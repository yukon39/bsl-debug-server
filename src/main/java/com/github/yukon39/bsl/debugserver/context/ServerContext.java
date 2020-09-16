package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.BSLDebugServer;
import com.github.yukon39.bsl.debugserver.configuration.DebugServerConfiguration;
import com.github.yukon39.bsl.debugserver.context.managers.*;
import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.github.yukon39.bsl.debugserver.debugee.data.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoExprEvaluated;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.lsp4j.debug.Thread;
import org.eclipse.lsp4j.debug.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerContext {

    private final ThreadsManager threadsManager = new ThreadsManager();
    private final BreakpointsManager breakpointsManager = new BreakpointsManager();
    private final StackTraceManager stackTraceManager = new StackTraceManager();
    private final SourceManager sourceManager = new SourceManager();
    private final VariablesManager variablesManager = new VariablesManager();

    @Getter
    private final Debugee debugee = new Debugee();
    private boolean configured = false;
    private DebugServerConfiguration configuration;
    private ScheduledExecutorService executor;

    private EventBus eventBus;

    public CompletableFuture<Void> initialize(DebugServerConfiguration configuration) {

        this.configuration = configuration;

        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(debugee, 1L, 1L, TimeUnit.SECONDS);

        configured = false;

        stackTraceManager.setSourceManager(sourceManager);

        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Void> attach(AttachRequestArguments args) {

        var configurationPath = Path.of(args.getSourcePath());

        if (!configurationPath.isAbsolute()) {
            configurationPath = Path.of(args.getCwd(), args.getSourcePath());
        }

        sourceManager.setConfigurationSource(configurationPath);

        var data = new HTTPServerInitialDebugSettingsData();

        return debugee.configure(args.getDebuggerURL(), args.getInfobaseAlias(), UUID.randomUUID())
                .thenCompose(aVoid -> debugee.attach(data))
                .thenRun(() -> postEvent(new InitializeEvent()));
    }

    public CompletableFuture<Void> restart(RestartArguments args) {

        var data = new HTTPServerInitialDebugSettingsData();

        return debugee.detach()
                .thenCompose(aVoid -> debugee.attach(data))
                .thenRun(() -> {
                });
    }

    public CompletableFuture<Void> disconnect(DisconnectArguments args) {
        if (args.getRestart()) {
            return CompletableFuture.completedFuture(null);
        } else {
            return debugee.detach();
        }
    }

    public CompletableFuture<SetBreakpointsResponse> setBreakpoints(SetBreakpointsArguments args) {

        var sourceContext = sourceManager.getSourceContext(args.getSource());
        var source = sourceContext.getSource();

        breakpointsManager.setBreakpoints(source, args.getBreakpoints());

        var breakpoints = breakpointsManager.getBreakpoints(source);

        Breakpoint[] array = new Breakpoint[breakpoints.size()];
        breakpoints.toArray(array);

        var result = new SetBreakpointsResponse();
        result.setBreakpoints(array);

        if (configured) {

            var moduleBPInfo = breakpointsManager.getModuleBPInfo();
            var bpWorkspace = new BPWorkspaceInternal();
            bpWorkspace.getModuleBPInfo().addAll(moduleBPInfo);

            return debugee.setBreakpoints(bpWorkspace)
                    .thenApply(aVoid -> result);

        } else {
            return CompletableFuture.completedFuture(result);
        }
    }

    public CompletableFuture<Void> configurationDone(ConfigurationDoneArguments args) {

        configured = true;

        var moduleBPInfo = breakpointsManager.getModuleBPInfo();
        var bpWorkspace = new BPWorkspaceInternal();
        bpWorkspace.getModuleBPInfo().addAll(moduleBPInfo);

        return CompletableFuture
                .completedFuture(bpWorkspace)
                .thenAccept(debugee::setBreakpoints);
    }

    public CompletableFuture<ThreadsResponse> threads() {

        return debugee.getAllTargetStates()
                .thenApply((targets) -> {
                    threadsManager.synchronizeDebugTargetStates(targets);
                    var threads = threadsManager.getThreads();

                    Thread[] array = new Thread[threads.size()];
                    threads.toArray(array);

                    var response = new ThreadsResponse();
                    response.setThreads(array);
                    return response;
                });
    }

    public CompletableFuture<Void> pause() {
        return debugee.setBreakOnNextStatement();
    }

    public CompletableFuture<ContinueResponse> stepContinue(ContinueArguments args) {

        var threadId = args.getThreadId();
        var threadContext = threadsManager.getThreadContext(threadId);

        var delayedExecutor = CompletableFuture.delayedExecutor(500L, TimeUnit.MILLISECONDS);

        return debugee.stepContinue(threadContext.getTargetId(), true)
                .thenRun(() -> log.debug("stepContinue for threadId={}", threadId))
                .thenApply(aVoid -> {
                    var response = new ContinueResponse();
                    response.setAllThreadsContinued(true);
                    return response;
                })
                .whenCompleteAsync((response, throwable) -> {
                    if (Objects.isNull(throwable)) {
                        var event = new StoppedEvent();
                        event.args.setThreadId(threadId);
                        event.args.setReason(StoppedEventArgumentsReason.STEP);
                        postEvent(event);
                    }
                }, delayedExecutor);
    }

    public CompletableFuture<Void> stepNext(NextArguments args) {

        var threadId = args.getThreadId();
        var threadContext = threadsManager.getThreadContext(threadId);

        return debugee.setBreakOnNextStatement()
                .thenCompose(aVoid -> debugee.stepContinue(threadContext.getTargetId(), true))
                .thenAccept(list -> {
                    var event = new StoppedEvent();
                    event.args.setThreadId(threadId);
                    event.args.setReason(StoppedEventArgumentsReason.STEP);
                    postEvent(event);
                })
                .thenRun(() -> log.debug("stepNext for threadId={}", threadId));
    }

    public CompletableFuture<Void> stepIn(StepInArguments args) {

        var threadId = args.getThreadId();
        var threadContext = threadsManager.getThreadContext(threadId);

        return debugee.stepIn(threadContext.getTargetId(), true)
                .thenAccept(list -> {
                    var event = new StoppedEvent();
                    event.args.setThreadId(threadId);
                    event.args.setReason(StoppedEventArgumentsReason.STEP);
                    postEvent(event);
                })
                .thenRun(() -> log.debug("stepIn for threadId={}", threadId));
    }

    public CompletableFuture<Void> stepOut(StepOutArguments args) {

        var threadId = args.getThreadId();
        var threadContext = threadsManager.getThreadContext(threadId);

        return debugee.stepOut(threadContext.getTargetId(), true)
                .thenAccept(list -> {
                    var event = new StoppedEvent();
                    event.args.setThreadId(threadId);
                    event.args.setReason(StoppedEventArgumentsReason.STEP);
                    postEvent(event);
                })
                .thenRun(() -> log.debug("stepOut success for threadId={}", threadId));
    }

    public CompletableFuture<StackTraceResponse> stackTrace(StackTraceArguments args) {

        var threadId = args.getThreadId();
        return CompletableFuture.supplyAsync(() -> {

            var threadContext = threadsManager.getThreadContext(threadId);
            var debugTargetId = threadContext.getTargetId();

            var stackFrames = stackTraceManager.getStackTrace(threadId);

            StackFrame[] array = new StackFrame[stackFrames.size()];
            stackFrames.toArray(array);

            var result = new StackTraceResponse();
            result.setStackFrames(array);
            result.setTotalFrames(stackFrames.size());

            return result;
        });
    }

    public CompletableFuture<ScopesResponse> scopes(ScopesArguments args) {

        var frameId = args.getFrameId();

        var stackFrameContext = stackTraceManager.getStackFrameContext(frameId);

        if (Objects.isNull(stackFrameContext)) {
            var throwable = new IllegalArgumentException("Unknown frameId=" + frameId);
            return CompletableFuture.failedFuture(throwable);
        }

        var variablesContext = variablesManager.createVariablesContext();
        variablesContext.setContextVariable(true);
        variablesContext.setStackFrameContext(stackFrameContext);

        var stackFrame = stackFrameContext.getStackFrame();
        var source = stackFrame.getSource();

        var threadContext = stackFrameContext.getThreadContext();
        var targetId = threadContext.getTargetId();
        var expression = variablesManager.getCalculationSource(variablesContext);

        return debugee.getLocalVariables(targetId, expression)
                .thenAccept(calculation -> {
                    if (Objects.nonNull(calculation)) {
                        variablesManager.setCalculationData(variablesContext, calculation);
                    }
                })
                .thenApply(aVoid -> {

                    var scope = new Scope();
                    scope.setName("Local variables"); // TODO: localize this
                    scope.setPresentationHint(ScopePresentationHint.LOCALS);
                    scope.setSource(source);
                    scope.setLine(stackFrame.getLine());
                    scope.setVariablesReference(variablesContext.getReference());

                    return List.of(scope);
                })
                .thenApply(scopes -> {
                    Scope[] array = new Scope[scopes.size()];
                    scopes.toArray(array);

                    var response = new ScopesResponse();
                    response.setScopes(array);

                    return response;
                });
    }

    public CompletableFuture<VariablesResponse> variables(VariablesArguments args) {

        var reference = args.getVariablesReference();
        var variablesContext = variablesManager.getVariablesContext(reference);

        if (Objects.isNull(variablesContext)) {
            return CompletableFuture.completedFuture(null);
        }

        CompletableFuture<List<Variable>> variablesFuture;

        if (variablesContext.isReceived()) {
            variablesFuture = CompletableFuture.completedFuture(variablesContext.getVariables());
        } else {

            var stackFrameContext = variablesContext.getStackFrameContext();
            var threadContext = stackFrameContext.getThreadContext();
            var targetId = threadContext.getTargetId();
            var expression = variablesManager.getCalculationSource(variablesContext);

            variablesFuture = debugee.evaluateExpressions(targetId, List.of(expression))
                    .thenApply(calculationResults -> {
                        if (!calculationResults.isEmpty()) {
                            variablesManager.setCalculationData(variablesContext, calculationResults.get(0));
                        }
                        return variablesContext.getVariables();
                    });
        }

        return variablesFuture
                .thenApply(variables -> {

                    Variable[] array = new Variable[variables.size()];
                    variables.toArray(array);

                    var response = new VariablesResponse();
                    response.setVariables(array);
                    return response;
                });
    }

    public CompletableFuture<SourceResponse> source(SourceArguments args) {
        return CompletableFuture.completedFuture(new SourceResponse());
    }

    public void debugTargetStarted(DBGUIExtCmdInfoStarted command) {

        try {

            var targetId = command.getTargetID();
            var threadContext = threadsManager.createThreadContext(targetId);
            postEvent(new ThreadEvent(threadContext.getThread(), ThreadEventArgumentsReason.STARTED));

        } catch (Exception e) {
            log.error("debugTargetStarted", e);
        }
    }

    public void debugTargetQuit(DBGUIExtCmdInfoQuit command) {

        try {

            var targetId = command.getTargetID();
            var threadContext = threadsManager.removeThreadContext(targetId);
            postEvent(new ThreadEvent(threadContext.getThread(), ThreadEventArgumentsReason.EXITED));

        } catch (Exception e) {
            log.error("debugTargetQuit", e);
        }
    }

    public void debugCallStackFormed(DBGUIExtCmdInfoCallStackFormed command) {

        try {

            var targetId = command.getTargetID();
            var callStack = command.getCallStack();

            var threadContext = threadsManager.getThreadContext(targetId);
            var thread = threadContext.getThread();

            stackTraceManager.setStackTrace(threadContext, callStack);

            var event = new StoppedEvent();
            event.args.setThreadId(thread.getId());

            if (command.getStopByBP()) {
                event.args.setReason(StoppedEventArgumentsReason.BREAKPOINT);
                event.args.setDescription("Breakpoint hit"); // TODO: localize this

            } else {
                event.args.setReason(StoppedEventArgumentsReason.PAUSE);
                event.args.setDescription("Paused!!!"); // TODO: localize this
            }

            postEvent(event);

        } catch (Exception e) {
            log.error("debugCallStackFormed", e);
            var event = new BSLDebugServer.OutputErrorEvent(e.getMessage());
            postEvent(event);
        }
    }

    public void debugExprEvaluated(DBGUIExtCmdInfoExprEvaluated command) {

        var calculationData = command.getEvalExprResBaseData();
        if (Objects.isNull(calculationData)) {
            return;
        }

        var variablesContext = variablesManager.getVariablesContext(calculationData.getExpressionResultID());
        if (Objects.isNull(variablesContext)) {
            return;
        }

        synchronized (variablesContext) {
            variablesManager.setCalculationData(variablesContext, calculationData);
            variablesContext.notifyAll();
        }
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(new ContextServerEventSubscriber(this));
        debugee.setEventBus(eventBus);
    }

    private void postEvent(Object event) {
        if (Objects.nonNull(eventBus)) {
            eventBus.post(event);
        }
    }

    public static class InitializeEvent {
    }

    public static class ThreadEvent {
        public final ThreadEventArguments args;

        ThreadEvent(Thread thread, String reason) {
            this.args = new ThreadEventArguments();
            this.args.setThreadId(thread.getId());
            this.args.setReason(reason);
        }
    }

    public static class StoppedEvent {
        public final StoppedEventArguments args = new StoppedEventArguments();
    }
}
