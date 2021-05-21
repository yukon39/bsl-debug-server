package com.github.yukon39.bsl.debug.client.impl;

import com.github.yukon39.bsl.debug.client.IDebuggerClient;
import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.*;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.DBGUIExtCmdInfoBase;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.*;
import com.github.yukon39.bsl.debug.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class DebuggerClient implements IDebuggerClient {

    private DebuggerClientExecutor executor;
    private SessionContext context;
    private DebuggerClientTargets targets;

    public void configure(URL debugServerURL, String infobaseAlias, UUID debugSession) {
        this.context = SessionContext.newInstance(infobaseAlias, debugSession);
        this.executor = DebuggerClientExecutor.newInstance(debugServerURL);
        this.targets = DebuggerClientTargets.newInstance(context, executor);
    }

    public CompletableFuture<String> getApiVersion() {

        var params = new RequestParameters("getRDbgAPIVer");

        var request = new MiscRDbgGetAPIVerRequest();

        return executor.executeAsync(request, params, MiscRDbgGetAPIVerResponse.class)
                .thenApply(response -> {
                    var apiVersion = response.getVersion();

                    log.debug(String.format("Provided API version is %s", apiVersion));

                    return apiVersion;
                });
    }

    public CompletableFuture<Void> test() {

        var params = new RequestParameters("test", "rdbgTest");

        var request = new RDBGTestRequest();

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("Test successful"));
    }

    public DebuggerClientTargets getTargetsInstance() {
        return targets;
    }

    public CompletableFuture<AttachDebugUIResult> attach(char[] password, DebuggerOptions options) {

        var params = new RequestParameters("attachDebugUI");

        var request = context.newSessionRequest(RDBGAttachDebugUIRequest.class);

        if (password.length > 0) {
            var credentials = StringUtils.toByteArray(password);
            request.setCredentials(credentials);
        }
        request.setOptions(options);

        return executor.executeAsync(request, params, RDBGAttachDebugUIResponse.class)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("Debug attach result is %s", result.toString()));

                    return result;
                });
    }

    public CompletableFuture<Boolean> detach() {

        var params = new RequestParameters("detachDebugUI");

        var request = context.newSessionRequest(RDBGDetachDebugUIRequest.class);

        return executor.executeAsync(request, params, RDBGDetachDebugUIResponse.class)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("Debug detach result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<Boolean> startUpdateIB() {

        var params = new RequestParameters("startUpdateIB");

        var request = context.newSessionRequest(RDBGStartUpdateIBRequest.class);

        return executor.executeAsync(request, params, RDBGStartUpdateIBResponse.class)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("StartUpdateIB result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<Boolean> finishUpdateIB() {

        var params = new RequestParameters("finishUpdateIB");

        var request = context.newSessionRequest(RDBGFinishUpdateIBRequest.class);

        return executor.executeAsync(request, params, RDBGFinishUpdateIBResponse.class)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("FinishUpdateIB result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<List<BSLModuleIdInternal>> getInaccessibleModules() {

        var params = new RequestParameters("getInaccessibleModules");

        var request = context.newSessionRequest(RDBGGetInaccessibleModulesRequest.class);

        return executor.executeAsync(request, params, RDBGGetInaccessibleModulesResponse.class)
                .thenApply(response -> {
                    var result = response.getModuleID();

                    log.debug(String.format("GetInaccessibleModules result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<Void> setInaccessibleModules(List<BSLModuleIdInternal> moduleId) {

        var params = new RequestParameters("setInaccessibleModules");

        var request = context.newSessionRequest(RDBGSetInaccessibleModulesRequest.class);
        request.getModuleID().addAll(moduleId);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("SetInaccessibleModules successful"));
    }

    public CompletableFuture<Void> initSettings(HTTPServerInitialDebugSettingsData data) {

        var params = new RequestParameters("initSettings");

        var request = context.newSessionRequest(RDBGSetInitialDebugSettingsRequest.class);
        request.setData(data);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("InitSettings successful"));
    }

    public CompletableFuture<Void> setAutoAttachSettings(DebugAutoAttachSettings autoAttachSettings) {

        var params = new RequestParameters("setAutoAttachSettings");

        var request = context.newSessionRequest(RDBGSetAutoAttachSettingsRequest.class);
        request.setAutoAttachSettings(autoAttachSettings);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("SetAutoAttachSettings successful"));
    }

    public CompletableFuture<Void> attachDetachDebugTargets(Boolean attach, List<DebugTargetIdLight> targets) {

        var params = new RequestParameters("attachDetachDbgTargets");

        var request = context.newSessionRequest(RDBGAttachDetachDebugTargetsRequest.class);
        request.setAttach(attach);
        request.getId().addAll(targets);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("attachDetachDebugTargets successful"));
    }

    public CompletableFuture<Void> terminateDebugTargets(List<DebugTargetId> targetID) {

        var params = new RequestParameters("terminateDbgTarget");

        var request = context.newSessionRequest(RDBGTerminateRequest.class);
        request.getTargetID().addAll(targetID);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("TerminateDebugTargets successful"));
    }

    public CompletableFuture<List<DbgTargetStateInfo>> getAllTargetStates() {

        var params = new RequestParameters("getDbgAllTargetStates");

        var request = context.newSessionRequest(RDBGGetDbgAllTargetStatesRequest.class);

        return executor.executeAsync(request, params, RDBGGetDbgAllTargetStatesResponse.class)
                .thenApply(response -> {
                    var result = response.getItem();

                    log.debug(String.format("GetAllTargetStates result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<DbgTargetState> getTargetState(DebugTargetIdLight id) {

        var params = new RequestParameters("getDbgTargetState");

        var request = context.newSessionRequest(RDBGGetDbgTargetStateRequest.class);
        request.setId(id);

        return executor.executeAsync(request, params, RDBGGetDbgTargetStateResponse.class)
                .thenApply(response -> {
                    var result = response.getState();

                    log.debug(String.format("getTargetState result is %s", result));

                    return result;
                });
    }

    public CompletableFuture<List<DBGUIExtCmdInfoBase>> ping() {

        var debugsession = context.getDebugSession();

        var params = new RequestParameters("pingDebugUIParams");
        params.setDebugID(debugsession);

        var request = new RDBGPingDebugUIRequest();

        return executor.executeAsync(request, params, RDBGPingDebugUIResponse.class)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("Ping result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<Void> setBreakOnNextStatement() {

        var params = new RequestParameters("setBreakOnNextStatement");

        var request = context.newSessionRequest(RDBGSetBreakOnNextStatementRequest.class);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("SetBreakOnNextStatement successful"));
    }

    public CompletableFuture<Void> clearBreakOnNextStatement() {

        var params = new RequestParameters("clearBreakOnNextStatement");

        var request = context.newSessionRequest(RDBGClearBreakOnNextStatementRequest.class);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("ClearBreakOnNextStatement successful"));
    }

    public CompletableFuture<Void> setBreakpoints(BPWorkspaceInternal bpWorkspace) {

        var params = new RequestParameters("setBreakpoints");

        var request = context.newSessionRequest(RDBGSetBreakpointsRequest.class);
        request.setBpWorkspace(bpWorkspace);

        return executor.executeAsync(request, params, RDBGEmptyResponse.class)
                .thenRun(() -> log.debug("SetBreakpoints successful"));
    }

    public CompletableFuture<List<DbgTargetStateInfo>> step(DebugTargetIdLight targetID, DebugStepAction action, Boolean simple) {

        var params = new RequestParameters("step");

        var request = context.newSessionRequest(RDBGStepRequest.class);
        request.setTargetID(targetID);
        request.setAction(action);
        request.setSimple(simple);

        return executor.executeAsync(request, params, RDBGStepResponse.class)
                .thenApply(response -> {
                    var result = response.getItem();

                    log.debug(String.format("Step result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<List<StackItemViewInfoData>> getCallStack(DebugTargetIdLight id) {

        var params = new RequestParameters("getCallStack");

        var request = context.newSessionRequest(RDBGGetCallStackRequest.class);
        request.setId(id);

        return executor.executeAsync(request, params, RDBGGetCallStackResponse.class)
                .thenApply(response -> {
                    var result = response.getCallStack();

                    log.debug(String.format("GetCallStack result size is %d", result.size()));

                    return result;
                });
    }

    public CompletableFuture<CalculationResultBaseData> evalLocalVariables(DebugTargetIdLight targetId,
                                                                           List<CalculationSourceDataStorage> expressions,
                                                                           Integer waitTime) {

        var params = new RequestParameters("evalLocalVariables");

        var request = context.newSessionRequest(RDBGEvalLocalVariablesRequest.class);
        request.setCalcWaitingTime(waitTime);
        request.setTargetID(targetId);
        request.getExpr().addAll(expressions);

        return executor.executeAsync(request, params, RDBGEvalLocalVariablesResponse.class)
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

        var params = new RequestParameters("evalExpr");

        var request = context.newSessionRequest(RDBGEvalExprRequest.class);
        request.setCalcWaitingTime(waitTime);
        request.setTargetID(targetId);
        request.getExpr().addAll(expressions);

        return executor.executeAsync(request, params, RDBGEvalExprResponse.class)
                .thenApply(response -> {
                    var result = response.getResult();

                    log.debug(String.format("evalExpression result size is %d", result.size()));

                    return result;
                });
    }
}
