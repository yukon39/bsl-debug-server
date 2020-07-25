package com.github.yukon39.bsl.debugserver.debugee;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.*;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoBase;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugClient;
import com.google.common.eventbus.EventBus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

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

        DBGUIExtCmdInfoBase[] commands;

        try {
            commands = ping().get();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return;
        }

        if (commands.length == 0) {
            log.debug("Ping result is empty");
            return;
        }

        log.debug("Ping result length " + commands.length);

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

        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Void> attach() {

        try {

            var attachResult = httpDebugClient.attach(password);
            httpDebugClient.initSettings();
            httpDebugClient.clearBreakOnNextStatement();
            httpDebugClient.setAutoAttachSettings(targetTypes, areaNames);

            attached = (attachResult == AttachDebugUIResult.REGISTERED);

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }

        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Void> detach() {

        if (attached) {
            attached = false;
            try {
                httpDebugClient.detach();
            } catch (Exception e) {
                return CompletableFuture.failedFuture(e);
            }
        }

        return CompletableFuture.completedFuture(null);
    }

    public CompletableFuture<Boolean> attachDebugTarget(DebugTargetIdLight target) {

        try {
            var targets = new ArrayList<DebugTargetIdLight>();
            targets.add(target);

            var result = httpDebugClient.attachDetachDebugTargets(true, targets);
            return CompletableFuture.completedFuture(result);

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Boolean> detachDebugTarget(DebugTargetIdLight target) {

        try {
            var targets = new ArrayList<DebugTargetIdLight>();
            targets.add(target);

            var result = httpDebugClient.attachDetachDebugTargets(false, targets);
            return CompletableFuture.completedFuture(result);

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<DBGUIExtCmdInfoBase[]> ping() {

        try {
            var result = httpDebugClient.ping();
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<List<DbgTargetStateInfo>> getAllTargetStates() {

        try {
            var result = httpDebugClient.getAllTargetStates();
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<DbgTargetState> getTargetState() {

        try {
            var result = httpDebugClient.getTargetState();
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<Void> setBreakOnNextStatement() {

        try {
            httpDebugClient.setBreakOnNextStatement();
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepContinue(DebugTargetIdLight targetID, Boolean simple) {
        try {
            var result = httpDebugClient.step(targetID, DebugStepAction.CONTINUE, simple);

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepIn(DebugTargetIdLight targetID, Boolean simple) {
        try {
            var result = httpDebugClient.step(targetID, DebugStepAction.STEP_IN, simple);

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<List<DbgTargetStateInfo>> stepOut(DebugTargetIdLight targetID, Boolean simple) {
        try {
            var result = httpDebugClient.step(targetID, DebugStepAction.STEP_OUT, simple);

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<List<StackItemViewInfoData>> getCallStack(DebugTargetIdLight id) {

        try {
            var result = httpDebugClient.getCallStack(id);
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    private void postEvent(Object event) {
        if (eventBus != null) {
            eventBus.post(event);
        }
    }

    public static class OutputEvent {
        public final String output;

        public OutputEvent(String output) {
            this.output = output;
        }
    }

    public class CmdStartedEvent {
        public final DBGUIExtCmdInfoStarted command;

        public CmdStartedEvent(DBGUIExtCmdInfoStarted command) {
            this.command = command;
        }
    }

    public class CmdQuitEvent {
        public final DBGUIExtCmdInfoQuit command;

        public CmdQuitEvent(DBGUIExtCmdInfoQuit command) {
            this.command = command;
        }
    }

    public class CmdCallStackFormedEvent {
        public final DBGUIExtCmdInfoCallStackFormed command;

        public CmdCallStackFormedEvent(DBGUIExtCmdInfoCallStackFormed command) {
            this.command = command;
        }
    }
}
