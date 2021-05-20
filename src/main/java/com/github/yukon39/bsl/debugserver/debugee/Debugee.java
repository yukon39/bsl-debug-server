package com.github.yukon39.bsl.debugserver.debugee;

import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.*;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.*;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.DebuggerOptions;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugClient;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Slf4j
public class Debugee implements Runnable {

    private static long WAIT_POLL = 500L;

    HTTPDebugClient httpDebugClient;

    private final Map<UUID, CalculationResultBaseData> calculatedExpressions = new HashMap<>();
    private final ReentrantLock calculationsLock = new ReentrantLock();
    private final Condition updateCondition = calculationsLock.newCondition();

    @Getter
    boolean attached = false;

    @Setter
    private char[] password = new char[]{};

    @Setter
    private List<DebugTargetType> targetTypes = DebugTargetType.defaultTargetTypes();

    @Setter
    private List<String> areaNames = new ArrayList<>();

    @Setter
    private EventBus eventBus;

    public Debugee() {
        httpDebugClient = new HTTPDebugClient();
    }

    @Override
    public void run() {

        if (!attached) {
            log.debug("Debugee not attached.");
            return;
        }

        List<DBGUIExtCmdInfoBase> commands;

        try {
            commands = ping().get();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return;
        }

        if (commands.isEmpty()) {
            log.debug("Ping result is empty");
            return;
        }

        log.debug("Ping result length " + commands.size());

        for (DBGUIExtCmdInfoBase command : commands) {
            log.debug("Ping result type " + command.getCmdId());

            if (command instanceof DBGUIExtCmdInfoStarted) {
                targetStartedEvent((DBGUIExtCmdInfoStarted) command);

            } else if (command instanceof DBGUIExtCmdInfoQuit) {
                targetQuitEvent((DBGUIExtCmdInfoQuit) command);

            } else if (command instanceof DBGUIExtCmdInfoCallStackFormed) {
                callStackFormedEvent((DBGUIExtCmdInfoCallStackFormed) command);

            } else if (command instanceof DBGUIExtCmdInfoExprEvaluated) {
                expressionEvaluatedEvent((DBGUIExtCmdInfoExprEvaluated) command);
            }
        }
    }

    public CompletableFuture<Void> configure(URL debuggerURI, String infobaseAlias, UUID debugSession) {

        httpDebugClient.configure(debuggerURI, infobaseAlias, debugSession);
        return httpDebugClient.test();

    }

    public CompletableFuture<AttachDebugUIResult> attach(HTTPServerInitialDebugSettingsData data) {

        var options = new DebuggerOptions();

        var autoAttachSettings = new DebugAutoAttachSettings();
        autoAttachSettings.getTargetType().addAll(targetTypes);
        autoAttachSettings.getAreaName().addAll(areaNames);

        var attach = httpDebugClient.attach(password, options);

        var attachActions = CompletableFuture.allOf(
                httpDebugClient.initSettings(data),
                httpDebugClient.clearBreakOnNextStatement(),
                httpDebugClient.setAutoAttachSettings(autoAttachSettings)
        );

        return attach
                .thenCombine(attachActions, (attachResult, aVoid) -> {
                    attached = (attachResult == AttachDebugUIResult.REGISTERED);
                    return attachResult;
                });
    }

    public CompletableFuture<Void> detach() {

        if (attached) {
            return httpDebugClient.detach()
                    .thenAccept(attachResult -> attached = false);
        } else {
            return CompletableFuture.completedFuture(null);
        }
    }

    public CompletableFuture<Void> attachDebugTarget(DebugTargetIdLight target) {

        var targets = new ArrayList<DebugTargetIdLight>();
        targets.add(target);

        return httpDebugClient.attachDetachDebugTargets(true, targets);
    }

    public CompletableFuture<Void> detachDebugTarget(DebugTargetIdLight target) {

        var targets = new ArrayList<DebugTargetIdLight>();
        targets.add(target);

        return httpDebugClient.attachDetachDebugTargets(false, targets);
    }

    public CompletableFuture<List<DBGUIExtCmdInfoBase>> ping() {
        return httpDebugClient.ping();
    }

    public CompletableFuture<List<DbgTargetStateInfo>> getAllTargetStates() {
        return httpDebugClient.getAllTargetStates();
    }

    public CompletableFuture<DbgTargetState> getTargetState(DebugTargetIdLight id) {
        return httpDebugClient.getTargetState(id);
    }

    public CompletableFuture<Void> setBreakOnNextStatement() {
        return httpDebugClient.setBreakOnNextStatement();
    }

    public CompletableFuture<Void> setBreakpoints(BPWorkspaceInternal bpWorkspace) {
        return httpDebugClient.setBreakpoints(bpWorkspace);
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepContinue(DebugTargetIdLight targetID, Boolean simple) {
        return httpDebugClient.step(targetID, DebugStepAction.CONTINUE, simple);
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepIn(DebugTargetIdLight targetID, Boolean simple) {
        return httpDebugClient.step(targetID, DebugStepAction.STEP_IN, simple);
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepOut(DebugTargetIdLight targetID, Boolean simple) {
        return httpDebugClient.step(targetID, DebugStepAction.STEP_OUT, simple);
    }

    public CompletableFuture<List<StackItemViewInfoData>> getCallStack(DebugTargetIdLight id) {
        return httpDebugClient.getCallStack(id);
    }

    public CompletableFuture<CalculationResultBaseData> getLocalVariables(
            DebugTargetIdLight targetId, CalculationSourceDataStorage expression) {

        var expressions = new ArrayList<CalculationSourceDataStorage>();
        expressions.add(expression);

        return httpDebugClient.evalLocalVariables(targetId, expressions, 100)
                .thenApply(calculatedData -> {
                    if (Objects.nonNull(calculatedData)) {
                        return calculatedData;
                    } else {
                        var resultId = expression.getSrcCalcInfo().getExpressionResultID();
                        return getCalculationFromQueue(resultId);
                    }
                });
    }

    public CompletableFuture<List<CalculationResultBaseData>> evaluateExpressions(
            DebugTargetIdLight targetId, List<CalculationSourceDataStorage> expr) {

        return httpDebugClient.evalExpression(targetId, expr, 100)
                .thenApply(calculatedDataList -> {

                    if (calculatedDataList.size() != expr.size()) {

                        var resultIds = expr.stream()
                                .filter(expression -> {
                                    var expressionResultId = expression.getSrcCalcInfo().getExpressionResultID();
                                    return calculatedDataList.stream().noneMatch(calculatedData -> {
                                        var dataResultId = calculatedData.getExpressionResultID();
                                        return dataResultId.equals(expressionResultId);
                                    });
                                })
                                .map(expression -> expression.getSrcCalcInfo().getExpressionResultID())
                                .collect(Collectors.toList());

                        var queueData = getCalculationsFromQueue(resultIds);
                        calculatedDataList.addAll(queueData);
                    }

                    return calculatedDataList;
                });
    }

    private CalculationResultBaseData getCalculationFromQueue(UUID resultId) {

        calculationsLock.lock();
        try {
            var isAwakened = true;
            while (!calculatedExpressions.containsKey(resultId) && isAwakened) {

                try {
                    isAwakened = updateCondition.await(10L, TimeUnit.SECONDS);
                } catch (Exception e) {
                    log.error("getLocalVariables", e);
                    throw new CompletionException(e);
                }
            }

            return calculatedExpressions.remove(resultId);

        } finally {
            calculationsLock.unlock();
        }
    }

    private List<CalculationResultBaseData> getCalculationsFromQueue(List<UUID> resultIds) {

        var result = new ArrayList<CalculationResultBaseData>();

        calculationsLock.lock();
        try {
            while (resultIds.size() != result.size()) {

                try {
                    updateCondition.await(10L, TimeUnit.SECONDS);
                } catch (Exception e) {
                    log.error("getLocalVariables", e);
                    throw new CompletionException(e);
                }

                resultIds.forEach(resultId -> {
                    if (calculatedExpressions.containsKey(resultId)) {
                        result.add(calculatedExpressions.remove(resultId));
                    }
                });
            }
        } finally {
            calculationsLock.unlock();
        }

        return result;
    }

    private void postEvent(Object event) {
        if (Objects.nonNull(eventBus)) {
            eventBus.post(event);
        }
    }

    private void targetStartedEvent(DBGUIExtCmdInfoStarted command) {

        var targetId = command.getTargetID();

        attachDebugTarget(targetId)
                .whenComplete((v, e) -> {
                            if (Objects.isNull(e)) {
                                postEvent(new CmdStartedEvent(command));
                            }
                        }
                );
    }

    private void targetQuitEvent(DBGUIExtCmdInfoQuit command) {

        var targetId = command.getTargetID();

        detachDebugTarget(targetId)
                .whenComplete((v, e) -> {
                            if (Objects.isNull(e)) {
                                postEvent(new CmdQuitEvent(command));
                            }
                        }
                );
    }

    private void callStackFormedEvent(DBGUIExtCmdInfoCallStackFormed command) {

        postEvent(new CmdCallStackFormedEvent(command));

//        var targetId = command.getTargetID();
//
//        getCallStack(targetId)
//                .thenApply(list -> command.getCallStack().addAll(list))
//        CompletableFuture
//                .supplyAsync(command::getCallStack)
//                .whenComplete((v, e) -> {
//                           if (Objects.isNull(e)) {
//                                postEvent(new CmdCallStackFormedEvent(command));
//                            } else {
//                                throw new CompletionException(e);
//                            }
//                        }
//                );
    }

    private void expressionEvaluatedEvent(DBGUIExtCmdInfoExprEvaluated command) {

        var calculatedData = command.getEvalExprResBaseData();

        if (Objects.isNull(calculatedData)) {
            return;
        }

        var resultId = calculatedData.getExpressionResultID();
        log.trace("[expressionEvaluatedEvent] resultId={}", resultId);

        calculationsLock.lock();
        try {
            calculatedExpressions.put(resultId, calculatedData);
            updateCondition.signalAll();
        } finally {
            calculationsLock.unlock();
        }

        postEvent(new CmdExprEvaluated(command));
    }

    public static class OutputEvent {
        public final String output;

        public OutputEvent(String output) {
            this.output = output;
        }
    }

    public static class CmdStartedEvent {
        public final DBGUIExtCmdInfoStarted command;

        public CmdStartedEvent(DBGUIExtCmdInfoStarted command) {
            this.command = command;
        }
    }

    public static class CmdQuitEvent {
        public final DBGUIExtCmdInfoQuit command;

        public CmdQuitEvent(DBGUIExtCmdInfoQuit command) {
            this.command = command;
        }
    }

    public static class CmdCallStackFormedEvent {
        public final DBGUIExtCmdInfoCallStackFormed command;

        public CmdCallStackFormedEvent(DBGUIExtCmdInfoCallStackFormed command) {
            this.command = command;
        }
    }

    public static class CmdExprEvaluated {
        public final DBGUIExtCmdInfoExprEvaluated command;

        public CmdExprEvaluated(DBGUIExtCmdInfoExprEvaluated command) {
            this.command = command;
        }
    }
}
