package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.configuration.DebugServerConfiguration;
import com.github.yukon39.bsl.debugserver.context.managers.SourceManager;
import com.github.yukon39.bsl.debugserver.context.managers.ThreadsManager;
import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.github.yukon39.bsl.debugserver.debugee.data.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.DbgPresentationOptionsOfStringValue;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.SourceCalculationDataInfo;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.ViewInterface;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
public class ServerContext {

    private final ThreadsManager threadsManager = new ThreadsManager();
    private final BreakpointsManager breakpointsManager = new BreakpointsManager();
    private final StackTraceManager stackTraceManager = new StackTraceManager();
    private final SourceManager sourceManager = SourceManager.create();
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

        return CompletableFuture
                .completedFuture(null)
                .thenCompose(v -> debugee.configure(args.getDebuggerURL(), args.getInfobaseAlias(), UUID.randomUUID()))
                .thenCompose(v -> debugee.attach(data))
                .thenRun(() -> postEvent(new InitializeEvent()));
    }

    public CompletableFuture<Void> restart(RestartArguments args) {

        var data = new HTTPServerInitialDebugSettingsData();

        return debugee.detach()
                .thenAccept(v -> debugee.attach(data));
    }

    public CompletableFuture<Void> disconnect(DisconnectArguments args) {
        if (args.getRestart()) {
            return CompletableFuture.completedFuture(null);
        } else {
            return debugee.detach();
        }
    }

    public CompletableFuture<SetBreakpointsResponse> setBreakpoints(SetBreakpointsArguments args) {

        var source = sourceManager.getSource(args.getSource().getPath());
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

            return CompletableFuture
                    .completedFuture(bpWorkspace)
                    .thenAccept(debugee::setBreakpoints)
                    .thenApply(v -> result);

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

        return CompletableFuture
                .supplyAsync(() -> threadsManager.getThreadContext(threadId))
                .thenCompose(threadContext -> debugee.stepContinue(threadContext.getTargetId(), true))
                .thenApply(v -> {
                    log.debug("stepContinue for threadId={}", threadId);

                    var result = new ContinueResponse();
                    result.setAllThreadsContinued(true);
                    return result;
                });
    }

    public CompletableFuture<Void> stepIn(StepInArguments args) {

        var threadId = args.getThreadId();

        return CompletableFuture
                .supplyAsync(() -> threadsManager.getThreadContext(threadId))
                .thenCompose(threadContext -> debugee.stepIn(threadContext.getTargetId(), true))
                .thenAccept(v -> log.debug("stepIn for threadId={}", threadId));
    }

    public CompletableFuture<Void> stepOut(StepOutArguments args) {

        var threadId = args.getThreadId();

        return CompletableFuture
                .supplyAsync(() -> threadsManager.getThreadContext(threadId))
                .thenCompose(threadContext -> debugee.stepOut(threadContext.getTargetId(), true))
                .thenAccept(v -> log.debug("stepOut success for threadId={}", threadId));
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

        var stackFrameContext = stackTraceManager.getStackFrameContext(args.getFrameId());
        if (Objects.isNull(stackFrameContext)) {
            return CompletableFuture.completedFuture(null);
        }

        var thread = stackFrameContext.getThread();
        if (Objects.isNull(thread)) {
            return CompletableFuture.completedFuture(null);
        }

        var threadContext = threadsManager.getThreadContext(thread);
        var targetId = threadContext.getTargetId();

        var variablesContext = variablesManager.createVariablesContext();
        variablesContext.setStackFrameContext(stackFrameContext);

        var expressions = new ArrayList<CalculationSourceDataStorage>();

        var calculationInfo = new SourceCalculationDataInfo();
        calculationInfo.setExpressionID(variablesContext.getResultId()); //"00000000-0000-0000-0000-000000000000"
        calculationInfo.setExpressionResultID(variablesContext.getResultId());
        calculationInfo.getInterfaces().add(ViewInterface.CONTEXT);

        var expression = new CalculationSourceDataStorage();
        expression.setStackLevel(stackFrameContext.getStackLevel());
        expression.setSrcCalcInfo(calculationInfo);
        expression.setPresOptions(DbgPresentationOptionsOfStringValue.defaultOptions());
        expressions.add(expression);

        var stackFrame = stackFrameContext.getStackFrame();

        return debugee.getLocalVariables(targetId, expressions)
                .thenApply(calculation -> {

                    var namedVariables = 0;

                    if (Objects.nonNull(calculation)) {
                        variablesManager.setCalculationData(variablesContext, calculation);
                        namedVariables = calculation.getCalculationResult().getValueOfContextPropInfo().size();
                    }

                    var scopes = new ArrayList<Scope>();

                    if (namedVariables > 0) {
                        var scope = new Scope();
                        scope.setName("Locals"); // TODO: localize this
                        scope.setPresentationHint(ScopePresentationHint.LOCALS);
                        scope.setSource(stackFrame.getSource());
                        scope.setLine(stackFrame.getLine());
                        scope.setVariablesReference(variablesContext.getReference());
                        scope.setNamedVariables(namedVariables);

                        scopes.add(scope);
                    }

                    Scope[] array = new Scope[scopes.size()];
                    scopes.toArray(array);

                    var response = new ScopesResponse();
                    response.setScopes(array);

                    return response;
                });
    }

    public CompletableFuture<VariablesResponse> variables(VariablesArguments args) {

        return CompletableFuture.supplyAsync(() -> {
            var reference = args.getVariablesReference();
            var variablesContext = variablesManager.getVariablesContext(reference);

            if (Objects.isNull(variablesContext)) {
                return null;
            }

            synchronized (variablesContext) {
                if (!variablesContext.isReceived()) {
                    try {
                        variablesContext.wait(10000L);
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                }
            }
            var variables = variablesContext.getVariables();

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

        var targetId = command.getTargetID();
        var threadContext = threadsManager.createThreadContext(targetId);
        postEvent(new ThreadEvent(threadContext.getThread(), ThreadEventArgumentsReason.STARTED));
    }

    public void debugTargetQuit(DBGUIExtCmdInfoQuit command) {

        var targetId = command.getTargetID();
        var threadContext = threadsManager.removeThreadContext(targetId);
        postEvent(new ThreadEvent(threadContext.getThread(), ThreadEventArgumentsReason.EXITED));
    }

    public void debugCallStackFormed(DBGUIExtCmdInfoCallStackFormed command) {

        var targetId = command.getTargetID();
        var threadContext = threadsManager.getThreadContext(targetId);
        var thread = threadContext.getThread();

        var callStack = command.getCallStack();

        stackTraceManager.setStackTrace(thread, callStack);

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
