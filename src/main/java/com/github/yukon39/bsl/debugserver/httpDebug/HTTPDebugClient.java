package com.github.yukon39.bsl.debugserver.httpDebug;

import com.github.yukon39.bsl.debugserver.debugee.data.DebuggerOptions;
import com.github.yukon39.bsl.debugserver.debugee.data.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.debugee.debugAutoAttach.DebugAutoAttachSettings;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.*;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationResultBaseData;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoBase;
import com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse.*;
import com.github.yukon39.bsl.debugserver.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import static java.net.http.HttpClient.Version;
import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpResponse.BodyHandlers;

@Slf4j
public class HTTPDebugClient {

    private final HttpClient httpClient = newHttpClient();
    private final HTTPDebugSerializer serializer = new HTTPDebugSerializer();
    private URL debugServerURL;
    private String infobaseAlias;
    private UUID debugSession;

    public void configure(URL debugServerURL, String infobaseAlias, UUID debugSession) {
        this.debugServerURL = debugServerURL;
        this.infobaseAlias = infobaseAlias;
        this.debugSession = debugSession;
    }

    public String getApiVersion() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("getRDbgAPIVer");

        var request = new MiscRDbgGetAPIVerRequest();
        var response = executeRequest(request, MiscRDbgGetAPIVerResponse.class, params);

        var apiVersion = response.getVersion();

        log.debug(String.format("Provided API version is %s", apiVersion));

        return apiVersion;
    }

    public void test() throws HTTPDebugException {

        var params = new RequestParameters()
                .setResource("rdbgTest")
                .setCommand("test");

        var request = new RDBGTestRequest();

        executeRequest(request, RDBGTestResponse.class, params);
    }

    public AttachDebugUIResult attach(char[] password, DebuggerOptions options) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("attachDebugUI");

        var request = new RDBGAttachDebugUIRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        if (password.length > 0) {
            var credentials = StringUtils.toByteArray(password);
            request.setCredentials(credentials);
        }
        request.setOptions(options);

        var response = executeRequest(request, RDBGAttachDebugUIResponse.class, params);
        var result = response.getResult();

        log.debug(String.format("Debug attach result is %s", result.toString()));

        return result;
    }

    public Boolean detach() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("detachDebugUI");

        var request = new RDBGDetachDebugUIRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        var response = executeRequest(request, RDBGDetachDebugUIResponse.class, params);
        var result = response.getResult();

        log.debug(String.format("Debug detach result is %s", result));

        return result;
    }

    public Boolean startUpdateIB() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("startUpdateIB");

        var request = new RDBGStartUpdateIBRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        var response = executeRequest(request, RDBGStartUpdateIBResponse.class, params);
        return response.getResult();
    }

    public Boolean finishUpdateIB() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("finishUpdateIB");

        var request = new RDBGFinishUpdateIBRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        var response = executeRequest(request, RDBGFinishUpdateIBResponse.class, params);
        return response.getResult();
    }

    public List<BSLModuleIdInternal> getInaccessibleModules() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("getInaccessibleModules");

        var request = new RDBGGetInaccessibleModulesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        var response = executeRequest(request, RDBGGetInaccessibleModulesResponse.class, params);
        return response.getModuleID();
    }

    public void setInaccessibleModules(List<BSLModuleIdInternal> moduleId) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("setInaccessibleModules");

        var request = new RDBGSetInaccessibleModulesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.getModuleID().addAll(moduleId);

        executeRequest(request, RDBGSetInaccessibleModulesResponse.class, params);
    }

    public void initSettings(HTTPServerInitialDebugSettingsData data) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("initSettings");

        var request = new RDBGSetInitialDebugSettingsRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setData(data);

        executeRequest(request, RDBGSetInitialDebugSettingsResponse.class, params);
    }

    public void setAutoAttachSettings(DebugTargetType[] targetTypes, String[] areaNames) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("setAutoAttachSettings");

        var autoAttachSettings = new DebugAutoAttachSettings();
        autoAttachSettings.setTargetType(targetTypes);
        autoAttachSettings.setAreaName(areaNames);

        var request = new RDBGSetAutoAttachSettingsRequest();
        request.setAutoAttachSettings(autoAttachSettings);
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        executeRequest(request, RDBGSetAutoAttachSettingsResponse.class, params);
    }

    public Boolean attachDetachDebugTargets(Boolean attach, List<DebugTargetIdLight> targets) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("attachDetachDbgTargets");

        var request = new RDBGAttachDetachDebugTargetsRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setAttach(attach);
        request.setId(targets);

        var response = executeRequest(request, RDBGAttachDetachDebugTargetsResponse.class, params);

        return response.getResult();
    }

    public void terminateDebugTargets(List<DebugTargetId> targetID) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("terminateDbgTarget");

        var request = new RDBGTerminateRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.getTargetID().addAll(targetID);

        executeRequest(request, RDBGTerminateResponse.class, params);
    }

    public List<DbgTargetStateInfo> getAllTargetStates() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("getDbgAllTargetStates");

        var request = new RDBGGetDbgAllTargetStatesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        var response = executeRequest(request, RDBGGetDbgAllTargetStatesResponse.class, params);

        return response.getItem();
    }

    public DbgTargetState getTargetState() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("getDbgTargetState");

        var request = new RDBGGetDbgTargetStateRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        var response = executeRequest(request, RDBGGetDbgTargetStateResponse.class, params);

        return response.getState();
    }

    public List<DBGUIExtCmdInfoBase> ping() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("pingDebugUIParams")
                .setDebugID(debugSession);

        var request = new RDBGPingDebugUIRequest();

        var response = executeRequest(request, RDBGPingDebugUIResponse.class, params);

        return response.getResult();
    }

    public void setBreakOnNextStatement() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("setBreakOnNextStatement");

        var request = new RDBGSetBreakOnNextStatementRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        executeRequest(request, RDBGSetBreakOnNextStatementResponse.class, params);
    }

    public void clearBreakOnNextStatement() throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("clearBreakOnNextStatement");

        var request = new RDBGClearBreakOnNextStatementRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        executeRequest(request, RDBGClearBreakOnNextStatementResponse.class, params);
    }

    public void setBreakpoints(BPWorkspaceInternal bpWorkspace) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("setBreakpoints");

        var request = new RDBGSetBreakpointsRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setBpWorkspace(bpWorkspace);

        executeRequest(request, RDBGSetBreakpointsResponse.class, params);
    }

    public List<DbgTargetStateInfo> step(DebugTargetIdLight targetID, DebugStepAction action, Boolean simple) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("step");

        var request = new RDBGStepRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setTargetID(targetID);
        request.setAction(action);
        // request.setSimple(simple);

        var response = executeRequest(request, RDBGStepResponse.class, params);

        return response.getItem();
    }

    public List<StackItemViewInfoData> getCallStack(DebugTargetIdLight id) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("getCallStack");

        var request = new RDBGGetCallStackRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setId(id);

        var response = executeRequest(request, RDBGGetCallStackResponse.class, params);
        return response.getCallStack();
    }

    public CalculationResultBaseData evalLocalVariables(DebugTargetIdLight targetId,
                                                        List<CalculationSourceDataStorage> expressions,
                                                        Integer waitTime) throws HTTPDebugException {

        var params = new RequestParameters()
                .setCommand("evalLocalVariables");

        var request = new RDBGEvalLocalVariablesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setCalcWaitingTime(waitTime);
        request.setTargetID(targetId);
        request.getExpr().addAll(expressions);

        var response = executeRequest(request, RDBGEvalLocalVariablesResponse.class, params);
        return response.getResult();
    }

    private HttpClient newHttpClient() {
        return HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(60))
                .build();
    }

    private <T> T executeRequest(IRDBGRequest command, Class<T> responseType, RequestParameters requestParameters) throws HTTPDebugException {

        String commandURL = String.format("%s/e1crdbg/%s", debugServerURL, requestParameters.toString());

        try {

            //            org.springframework.web.util.UriComponentsBuilder
//            final URL myUrl = UriComponentsBuilder
//                    .fromHttpUrl("http://IP:4567/foldername/1234?abc=xyz")
//                    .build()
//                    .toUri()
//                    .toURL();


            var body = serializer.serialize(command);

            var httpRequest = HttpRequest.newBuilder(URI.create(commandURL))
                    //.header("Accept", "application/json")
                    //.header("Accept-Encoding", "gzip")
                    //.header("Content-Type", "application/json; charset=utf-8")
                    .header("Accept", "application/xml")
                    .header("Content-Type", "application/xml; charset=utf-8")
                    .header("Accept-Encoding", "gzip")
                    .header("User-Agent", "1CV8")
                    .POST(BodyPublishers.ofString(body))
                    .build();


            var httpResponse = httpClient.sendAsync(httpRequest, BodyHandlers.ofByteArray());

            var response = httpResponse.get();
            var responseBody = response.body();

            if (responseBody.length == 0) {
                return responseType.getDeclaredConstructor().newInstance();
            } else {
                return serializer.deserialize(responseBody, responseType);
            }

        } catch (Exception e) {
            log.error("executeRequest", e);
            throw new HTTPDebugException(e);
        }
    }

    private class RequestParameters {
        private final HashMap<String, String> parameters = new HashMap<>();
        private String resource = "rdbg";

        private RequestParameters setCommand(String commandName) {
            parameters.put("cmd", commandName);
            return this;
        }

        private RequestParameters setDebugID(UUID debugID) {
            parameters.put("dbgui", debugID.toString());
            return this;
        }

        private RequestParameters setResource(String resource) {
            this.resource = resource;
            return this;
        }

        @Override
        public String toString() {
            var result = new StringJoiner("&");
            for (var entry : parameters.entrySet()) {
                result.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
            }
            return resource + "?" + result.toString();
        }
    }
}
