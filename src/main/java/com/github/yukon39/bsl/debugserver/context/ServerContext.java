package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.configuration.DebugServerConfiguration;
import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.github.yukon39.bsl.debugserver.debugee.data.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import org.eclipse.lsp4j.debug.Thread;
import org.eclipse.lsp4j.debug.*;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.*;

public class ServerContext {

    private final ThreadsManager threadsManager = new ThreadsManager(this);
    private final BreakpointsManager breakpointsManager = new BreakpointsManager();
    private final StackTraceManager stackTraceManager = new StackTraceManager();
    private final SourceManager sourceManager = SourceManager.create();
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

                    Thread [] array = new Thread[threads.size()];
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

        return CompletableFuture
                .supplyAsync(() -> {
                    var threadId = args.getThreadId();
                    return threadsManager.getDebugTargetId(threadId);
                })
                .thenAccept(debugTargetId -> debugee.stepContinue(debugTargetId, true))
                .thenApply(list -> {
                    var result = new ContinueResponse();
                    result.setAllThreadsContinued(false);
                    return result;
                });
    }

    public CompletableFuture<Void> stepIn(StepInArguments args) {

        return CompletableFuture
                .supplyAsync(() -> {
                    var threadId = args.getThreadId();
                    return threadsManager.getDebugTargetId(threadId);
                })
                .thenAccept(debugTargetId -> debugee.stepIn(debugTargetId, true));
    }

    public CompletableFuture<Void> stepOut(StepOutArguments args) {

        return CompletableFuture
                .supplyAsync(() -> {
                    var threadId = args.getThreadId();
                    return threadsManager.getDebugTargetId(threadId);
                })
                .thenAccept(debugTargetId -> debugee.stepOut(debugTargetId, true));
    }

    public CompletableFuture<StackTraceResponse> stackTrace(StackTraceArguments args) {

        return CompletableFuture.supplyAsync(() -> {

            var threadId = args.getThreadId();
            var debugTargetId = threadsManager.getDebugTargetId(threadId);
            if (Objects.isNull(debugTargetId)) {
                return new StackTraceResponse();
            }

            // var callStack = debugee.getCallStack(debugTargetId).get();
            // stackTraceManager.setStackTrace(threadId, callStack);
            var stackFrames = stackTraceManager.getStackTrace(threadId);

            StackFrame [] array = new StackFrame[stackFrames.size()];
            stackFrames.toArray(array);

            var result = new StackTraceResponse();
            result.setStackFrames(array);
            result.setTotalFrames(stackFrames.size());

            return result;
        });
    }

    public CompletableFuture<ScopesResponse> scopes(ScopesArguments args) {

        var result = new ScopesResponse();
        result.setScopes(new Scope[]{});

        return CompletableFuture.completedFuture(result);
    }

    public CompletableFuture<SourceResponse> source(SourceArguments args) {
        return CompletableFuture.completedFuture(new SourceResponse());
    }

    public void debugTargetStarted(DBGUIExtCmdInfoStarted command) {

        var targetId = command.getTargetID();

        try {
            debugee.attachDebugTarget(targetId).get();
        } catch (Exception e) {
            // TODO: write log
        }

        var thread = threadsManager.addDebugTarget(targetId);
        postEvent(new ThreadEvent(thread.getId(), ThreadEventArgumentsReason.STARTED));
    }

    public void debugTargetQuit(DBGUIExtCmdInfoQuit command) {

        var targetId = command.getTargetID();

        try {
            debugee.detachDebugTarget(targetId).get();
        } catch (Exception e) {
            // TODO: write log
        }

        var thread = threadsManager.removeDebugTarget(targetId);
        postEvent(new ThreadEvent(thread.getId(), ThreadEventArgumentsReason.EXITED));
    }

    public void debugCallStackFormed(DBGUIExtCmdInfoCallStackFormed command) {

        var thread = threadsManager.getThread(command.getTargetID());

        if (Objects.isNull(thread)) {
            return;
        }

        // try {

        var callStack = command.getCallStack();

        stackTraceManager.setStackTrace(thread.getId(), callStack);
        var stackFrames = stackTraceManager.getStackTrace(thread.getId());

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

        // } catch (Exception e) {
        //
        // var sw = new StringWriter();
        // e.printStackTrace(new PrintWriter(sw));
        // String exceptionAsString = sw.toString();
        //
        // postEvent(new Debugee.OutputEvent(e.getMessage() + System.lineSeparator() +
        // exceptionAsString));
        // }
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

        ThreadEvent(Integer id, String reason) {
            this.args = new ThreadEventArguments();
            this.args.setThreadId(id);
            this.args.setReason(reason);
        }
    }

    public static class StoppedEvent {
        public final StoppedEventArguments args = new StoppedEventArguments();
    }
}
