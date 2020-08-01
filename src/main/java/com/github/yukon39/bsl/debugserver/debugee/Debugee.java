package com.github.yukon39.bsl.debugserver.debugee;

import com.github.yukon39.bsl.debugserver.debugee.data.DebuggerOptions;
import com.github.yukon39.bsl.debugserver.debugee.data.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.*;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoBase;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugClient;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugException;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Slf4j
public class Debugee implements Runnable {

    private final HTTPDebugClient httpDebugClient;

    @Getter
    private boolean attached = false;

    @Setter
    private char[] password = new char[]{};

    @Setter
    private DebugTargetType[] targetTypes = DebugTargetType.defaultTargetTypes();

    @Setter
    private String[] areaNames = new String[]{};

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
                postEvent(new CmdStartedEvent((DBGUIExtCmdInfoStarted) command));

            } else if (command instanceof DBGUIExtCmdInfoQuit) {
                postEvent(new CmdQuitEvent((DBGUIExtCmdInfoQuit) command));

            } else if (command instanceof DBGUIExtCmdInfoCallStackFormed) {
                postEvent(new CmdCallStackFormedEvent((DBGUIExtCmdInfoCallStackFormed) command));
            }
        }
    }

    public CompletableFuture<Void> configure(URL debuggerURI, String infobaseAlias, UUID debugSession) {

        httpDebugClient.configure(debuggerURI, infobaseAlias, debugSession);
        try {
            httpDebugClient.test();
            return CompletableFuture.completedFuture(null);
        } catch (HTTPDebugException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<AttachDebugUIResult> attach(HTTPServerInitialDebugSettingsData data) {

        var options = new DebuggerOptions();

        try {
            var attachResult = httpDebugClient.attach(password, options);
            httpDebugClient.initSettings(data);
            httpDebugClient.clearBreakOnNextStatement();
            httpDebugClient.setAutoAttachSettings(targetTypes, areaNames);

            attached = true;

            return CompletableFuture.completedFuture(attachResult);
        } catch (HTTPDebugException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Void> detach() {

        return CompletableFuture.runAsync(() -> {
            if (attached) {
                attached = false;
                try {
                    httpDebugClient.detach();
                } catch (HTTPDebugException e) {
                    throw new CompletionException(e);
                }
            }
        });
    }

    public CompletableFuture<Boolean> attachDebugTarget(DebugTargetIdLight target) {

        return CompletableFuture.supplyAsync(() -> {

            try {
                var targets = new ArrayList<DebugTargetIdLight>();
                targets.add(target);

                return httpDebugClient.attachDetachDebugTargets(true, targets);

            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Boolean> detachDebugTarget(DebugTargetIdLight target) {

        return CompletableFuture.supplyAsync(() -> {

            try {
                var targets = new ArrayList<DebugTargetIdLight>();
                targets.add(target);

                return httpDebugClient.attachDetachDebugTargets(false, targets);

            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<List<DBGUIExtCmdInfoBase>> ping() {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpDebugClient.ping();
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<List<DbgTargetStateInfo>> getAllTargetStates() {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpDebugClient.getAllTargetStates();
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<DbgTargetState> getTargetState() {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpDebugClient.getTargetState();
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Void> setBreakOnNextStatement() {

        return CompletableFuture.runAsync(() -> {
            try {
                httpDebugClient.setBreakOnNextStatement();
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<Void> setBreakpoints(BPWorkspaceInternal bpWorkspace) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                httpDebugClient.setBreakpoints(bpWorkspace);
                return null;
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });

    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepContinue(DebugTargetIdLight targetID, Boolean simple) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpDebugClient.step(targetID, DebugStepAction.CONTINUE, simple);
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepIn(DebugTargetIdLight targetID, Boolean simple) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpDebugClient.step(targetID, DebugStepAction.STEP_IN, simple);
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepOut(DebugTargetIdLight targetID, Boolean simple) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpDebugClient.step(targetID, DebugStepAction.STEP_OUT, simple);
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    public CompletableFuture<List<StackItemViewInfoData>> getCallStack(DebugTargetIdLight id) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                return httpDebugClient.getCallStack(id);
            } catch (HTTPDebugException e) {
                throw new CompletionException(e);
            }
        });
    }

    private void postEvent(Object event) {
        if (Objects.nonNull(eventBus)) {
            eventBus.post(event);
        }
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
}
