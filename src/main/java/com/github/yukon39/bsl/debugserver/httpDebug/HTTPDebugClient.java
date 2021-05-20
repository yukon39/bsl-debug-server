package com.github.yukon39.bsl.debugserver.httpDebug;

import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.*;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.DBGUIExtCmdInfoBase;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.*;
import com.github.yukon39.bsl.debug.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.net.http.HttpClient.Version;
import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpResponse.BodyHandlers;

@Slf4j
public class HTTPDebugClient {

    private final HttpClient httpClient = newHttpClient();
    private URL debugServerURL;
    String infobaseAlias;
    UUID debugSession;

    public void configure(URL debugServerURL, String infobaseAlias, UUID debugSession) {
        this.debugServerURL = debugServerURL;
        this.infobaseAlias = infobaseAlias;
        this.debugSession = debugSession;
    }

    public CompletableFuture<String> getApiVersion() {

        var params = new RequestParameters()
                .setCommand("getRDbgAPIVer");

        var request = new MiscRDbgGetAPIVerRequest();

        return execute(request, MiscRDbgGetAPIVerResponse.class, params)
                .thenApply(response -> {
                    var apiVersion = response.getVersion();

                    log.debug(String.format("Provided API version is %s", apiVersion));

                    return apiVersion;
                });
    }

    public CompletableFuture<Void> test() {

        var params = new RequestParameters()
                .setResource("rdbgTest")
                .setCommand("test");

        var request = new RDBGTestRequest();

        return execute(request, RDBGTestResponse.class, params)
                .thenRun(() -> log.debug("Test successful"));
    }

    public CompletableFuture<AttachDebugUIResult> attach(char[] password, DebuggerOptions options) {

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

        return execute(request, RDBGAttachDebugUIResponse.class, params)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("Debug attach result is %s", result.toString()));

                    return result;
                });
    }

    public CompletableFuture<Boolean> detach() {

        var params = new RequestParameters()
                .setCommand("detachDebugUI");

        var request = new RDBGDetachDebugUIRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        return execute(request, RDBGDetachDebugUIResponse.class, params)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("Debug detach result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<Boolean> startUpdateIB() {

        var params = new RequestParameters()
                .setCommand("startUpdateIB");

        var request = new RDBGStartUpdateIBRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        return execute(request, RDBGStartUpdateIBResponse.class, params)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("StartUpdateIB result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<Boolean> finishUpdateIB() {

        var params = new RequestParameters()
                .setCommand("finishUpdateIB");

        var request = new RDBGFinishUpdateIBRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        return execute(request, RDBGFinishUpdateIBResponse.class, params)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("FinishUpdateIB result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<List<BSLModuleIdInternal>> getInaccessibleModules() {

        var params = new RequestParameters()
                .setCommand("getInaccessibleModules");

        var request = new RDBGGetInaccessibleModulesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        return execute(request, RDBGGetInaccessibleModulesResponse.class, params)
                .thenApply(response -> {
                    var result = response.getModuleID();

                    log.debug(String.format("GetInaccessibleModules result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<Void> setInaccessibleModules(List<BSLModuleIdInternal> moduleId) {

        var params = new RequestParameters()
                .setCommand("setInaccessibleModules");

        var request = new RDBGSetInaccessibleModulesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.getModuleID().addAll(moduleId);

        return execute(request, RDBGSetInaccessibleModulesResponse.class, params)
                .thenRun(() -> log.debug("SetInaccessibleModules successful"));
    }

    public CompletableFuture<Void> initSettings(HTTPServerInitialDebugSettingsData data) {

        var params = new RequestParameters()
                .setCommand("initSettings");

        var request = new RDBGSetInitialDebugSettingsRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setData(data);

        return execute(request, RDBGSetInitialDebugSettingsResponse.class, params)
                .thenRun(() -> log.debug("InitSettings successful"));
    }

    public CompletableFuture<Void> setAutoAttachSettings(DebugAutoAttachSettings autoAttachSettings) {

        var params = new RequestParameters()
                .setCommand("setAutoAttachSettings");

        var request = new RDBGSetAutoAttachSettingsRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setAutoAttachSettings(autoAttachSettings);

        return execute(request, RDBGSetAutoAttachSettingsResponse.class, params)
                .thenRun(() -> log.debug("SetAutoAttachSettings successful"));
    }

    public CompletableFuture<Void> attachDetachDebugTargets(Boolean attach, List<DebugTargetIdLight> targets) {

        var params = new RequestParameters()
                .setCommand("attachDetachDbgTargets");

        var request = new RDBGAttachDetachDebugTargetsRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setAttach(attach);
        request.setId(targets);

        return execute(request, RDBGAttachDetachDebugTargetsResponse.class, params)
                .thenRun(() -> log.debug("attachDetachDebugTargets successful"));
    }

    public CompletableFuture<Void> terminateDebugTargets(List<DebugTargetId> targetID) {

        var params = new RequestParameters()
                .setCommand("terminateDbgTarget");

        var request = new RDBGTerminateRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.getTargetID().addAll(targetID);

        return execute(request, RDBGTerminateResponse.class, params)
                .thenRun(() -> log.debug("TerminateDebugTargets successful"));
    }

    public CompletableFuture<List<DbgTargetStateInfo>> getAllTargetStates() {

        var params = new RequestParameters()
                .setCommand("getDbgAllTargetStates");

        var request = new RDBGGetDbgAllTargetStatesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        return execute(request, RDBGGetDbgAllTargetStatesResponse.class, params)
                .thenApply(response -> {
                    var result = response.getItem();

                    log.debug(String.format("GetAllTargetStates result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<DbgTargetState> getTargetState(DebugTargetIdLight id) {

        var params = new RequestParameters()
                .setCommand("getDbgTargetState");

        var request = new RDBGGetDbgTargetStateRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setId(id);

        return execute(request, RDBGGetDbgTargetStateResponse.class, params)
                .thenApply(response -> {
                    var result = response.getState();

                    log.debug(String.format("getTargetState result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<List<DBGUIExtCmdInfoBase>> ping() {

        var params = new RequestParameters()
                .setCommand("pingDebugUIParams")
                .setDebugID(debugSession);

        var request = new RDBGPingDebugUIRequest();

        return execute(request, RDBGPingDebugUIResponse.class, params)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("Ping result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<Void> setBreakOnNextStatement() {

        var params = new RequestParameters()
                .setCommand("setBreakOnNextStatement");

        var request = new RDBGSetBreakOnNextStatementRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        return execute(request, RDBGSetBreakOnNextStatementResponse.class, params)
                .thenRun(() -> log.debug("SetBreakOnNextStatement successful"));
    }

    public CompletableFuture<Void> clearBreakOnNextStatement() {

        var params = new RequestParameters()
                .setCommand("clearBreakOnNextStatement");

        var request = new RDBGClearBreakOnNextStatementRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);

        return execute(request, RDBGClearBreakOnNextStatementResponse.class, params)
                .thenRun(() -> log.debug("ClearBreakOnNextStatement successful"));
    }

    public CompletableFuture<Void> setBreakpoints(BPWorkspaceInternal bpWorkspace) {

        var params = new RequestParameters()
                .setCommand("setBreakpoints");

        var request = new RDBGSetBreakpointsRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setBpWorkspace(bpWorkspace);

        return execute(request, RDBGSetBreakpointsResponse.class, params)
                .thenRun(() -> log.debug("SetBreakpoints successful"));
    }

    public CompletableFuture<List<DbgTargetStateInfo>> step(DebugTargetIdLight targetID, DebugStepAction action, Boolean simple) {

        var params = new RequestParameters()
                .setCommand("step");

        var request = new RDBGStepRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setTargetID(targetID);
        request.setAction(action);
        request.setSimple(simple);

        return execute(request, RDBGStepResponse.class, params)
                .thenApply(response -> {
                    var result = response.getItem();

                    log.debug(String.format("Step result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<List<StackItemViewInfoData>> getCallStack(DebugTargetIdLight id) {

        var params = new RequestParameters()
                .setCommand("getCallStack");

        var request = new RDBGGetCallStackRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setId(id);

        return execute(request, RDBGGetCallStackResponse.class, params)
                .thenApply(response -> {
                    var result = response.getCallStack();

                    log.debug(String.format("GetCallStack result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<CalculationResultBaseData> evalLocalVariables(DebugTargetIdLight targetId,
                                                                           List<CalculationSourceDataStorage> expressions,
                                                                           Integer waitTime) {

        var params = new RequestParameters()
                .setCommand("evalLocalVariables");

        var request = new RDBGEvalLocalVariablesRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setCalcWaitingTime(waitTime);
        request.setTargetID(targetId);
        request.getExpr().addAll(expressions);

        return execute(request, RDBGEvalLocalVariablesResponse.class, params)
                .thenApply(response -> {
                    var result = response.getResult();

                    if (Objects.nonNull(result)) {
                        log.debug(String.format("evalLocalVariables result is %s", result.getEvalResultState()));
                    } else {
                        log.debug("evalLocalVariables result is empty");
                    }

                    return result;
                });
    }

    public CompletableFuture<List<CalculationResultBaseData>> evalExpression(DebugTargetIdLight targetId,
                                                          List<CalculationSourceDataStorage> expressions,
                                                          Integer waitTime) {

        var params = new RequestParameters()
                .setCommand("evalExpr");

        var request = new RDBGEvalExprRequest();
        request.setIdOfDebuggerUI(debugSession);
        request.setInfoBaseAlias(infobaseAlias);
        request.setCalcWaitingTime(waitTime);
        request.setTargetID(targetId);
        request.getExpr().addAll(expressions);

        return execute(request, RDBGEvalExprResponse.class, params)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("evalExpression result size is %d", result.size()));

                    return result;
                });
    }

    private HttpClient newHttpClient() {
        return HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(60))
                .build();
    }

    <T> CompletableFuture<T> execute(IRDBGRequest command, Class<T> responseType, RequestParameters requestParameters) {

        String commandURL = String.format("%s/e1crdbg/%s", debugServerURL, requestParameters.toString());

        return CompletableFuture
                .completedFuture(command)
                .thenApply(request -> {
                    try {
                        return DebuggerXmlSerializer.serialize(request);
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                })
                .thenApply(body -> HttpRequest.newBuilder(URI.create(commandURL))
                        .header("Accept", "application/xml")
                        .header("Content-Type", "application/xml; charset=utf-8")
                        .header("Accept-Encoding", "gzip")
                        .header("User-Agent", "1CV8")
                        .POST(BodyPublishers.ofString(body))
                        .build())
                .thenCompose(httpRequest -> httpClient.sendAsync(httpRequest, BodyHandlers.ofByteArray()))
                .thenApply(HttpResponse::body)
                .thenApply(responseBody -> {
                    try {
                        if (responseBody.length == 0) {
                            return responseType.getDeclaredConstructor().newInstance();
                        } else {
                            return DebuggerXmlSerializer.deserialize(responseBody, responseType);
                        }
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                })
                .handle((response, throwable) -> {
                    if (Objects.isNull(throwable)) {
                        return response;
                    } else {
                        var httpDebugException = new HTTPDebugException(throwable);
                        log.error("[executeRequest]", throwable);
                        throw new CompletionException(httpDebugException);
                    }
                });
    }

    static class RequestParameters {
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
            parameters.forEach((key, value) -> result.add(String.format("%s=%s", key, value)));
            return resource + "?" + result.toString();
        }
    }
}
