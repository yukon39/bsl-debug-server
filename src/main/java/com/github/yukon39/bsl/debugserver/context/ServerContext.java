package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.*;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import org.eclipse.lsp4j.debug.Thread;
import org.eclipse.lsp4j.debug.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;
import java.util.concurrent.*;

public class ServerContext {

    private final ThreadsManager threadsManager = new ThreadsManager(this);
    private final BreakpointsManager breakpointsManager = new BreakpointsManager();
    private final StackTraceManager stackTraceManager = new StackTraceManager(this);

    @Getter
    private final Debugee debugee = new Debugee();
    private ScheduledExecutorService executor;

    private EventBus eventBus;

    public void initialize() {

        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(debugee, 1L, 1L, TimeUnit.SECONDS);
    }

    public void attach(AttachRequestArguments args) throws ExecutionException, InterruptedException {
        debugee.configure(args.getDebuggerURL(), args.getInfobaseAlias(), UUID.randomUUID()).get();
        postEvent(new InitializeEvent());
    }

    public void restart(RestartArguments args) throws ExecutionException, InterruptedException {
        debugee.detach().get();
        debugee.attach().get();
    }

    public void disconnect(DisconnectArguments args) throws ExecutionException, InterruptedException {
        if (args.getRestart() == false) {
            debugee.detach().get();
        }
    }

    public void configurationDone(ConfigurationDoneArguments args) throws ExecutionException, InterruptedException {
        debugee.attach().get();
    }

    public ThreadsResponse threads() throws ExecutionException, InterruptedException {

        var targets = debugee.getAllTargetStates().get();
        threadsManager.synchronizeDebugTargetStates(targets);

        var threads = threadsManager.getThreads();

        Thread[] array = new Thread[threads.size()];
        threads.toArray(array);

        var response = new ThreadsResponse();
        response.setThreads(array);
        return response;
    }

    public void pause() throws ExecutionException, InterruptedException {
        debugee.setBreakOnNextStatement().get();
    }

    public ContinueResponse stepContinue(ContinueArguments args) throws ExecutionException, InterruptedException {

        var threadId = args.getThreadId();
        var debugTargetId = threadsManager.getDebugTargetId(threadId);

        if (debugTargetId != null) {
            debugee.stepContinue(debugTargetId, true).get();
        }

        var result = new ContinueResponse();
        result.setAllThreadsContinued(false);
        return result;
    }

    public void stepIn(StepInArguments args) throws ExecutionException, InterruptedException {

        var threadId = args.getThreadId();
        var debugTargetId = threadsManager.getDebugTargetId(threadId);

        if (debugTargetId != null) {
            debugee.stepIn(debugTargetId, true).get();
        }
    }

    public void stepOut(StepOutArguments args) throws ExecutionException, InterruptedException {

        var threadId = args.getThreadId();
        var debugTargetId = threadsManager.getDebugTargetId(threadId);

        if (debugTargetId != null) {
            debugee.stepOut(debugTargetId, true).get();
        }
    }

    public StackTraceResponse stackTrace(StackTraceArguments args) throws ExecutionException, InterruptedException {

        var threadId = args.getThreadId();
        var debugTargetId = threadsManager.getDebugTargetId(threadId);
        if (debugTargetId == null) {
            return new StackTraceResponse();
        }

        // var callStack = debugee.getCallStack(debugTargetId).get();
        // stackTraceManager.setStackTrace(threadId, callStack);
        var stackFrames = stackTraceManager.getStackTrace(threadId);

        // .subList(0, Math.min(callStack.size(), levels))

        StackFrame[] array = new StackFrame[stackFrames.size()];
        stackFrames.toArray(array);

        var result = new StackTraceResponse();
        result.setStackFrames(array);
        result.setTotalFrames(stackFrames.size());

        return result;
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

        if (thread == null) {
            return;
        }

        // try {

        var callStack = command.getCallStack();

        stackTraceManager.setStackTrace(thread.getId(), callStack);
        var stackFrames = stackTraceManager.getStackTrace(thread.getId());

        var event = new StoppedEvent();
        event.args.setReason(StoppedEventArgumentsReason.PAUSE);
        event.args.setThreadId(thread.getId());
        event.args.setDescription("Paused!!!"); // TODO: localize this

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
        if (eventBus != null) {
            eventBus.post(event);
        }
    }

    public class InitializeEvent {
    }

    public class ThreadEvent {
        public final ThreadEventArguments args;

        ThreadEvent(Integer id, String reason) {
            this.args = new ThreadEventArguments();
            this.args.setThreadId(id);
            this.args.setReason(reason);
        }
    }

    public class StoppedEvent {
        public final StoppedEventArguments args = new StoppedEventArguments();
    }
}
