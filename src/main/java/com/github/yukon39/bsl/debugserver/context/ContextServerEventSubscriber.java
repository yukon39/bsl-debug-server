package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.google.common.eventbus.Subscribe;

public class ContextServerEventSubscriber {
    private final ServerContext context;

    public ContextServerEventSubscriber(ServerContext context) {
        this.context = context;
    }

    @Subscribe
    public void debugeeThreadStarted(Debugee.CmdStartedEvent event) {
        context.debugTargetStarted(event.command);
    }

    @Subscribe
    public void debugeeThreadExited(Debugee.CmdQuitEvent event) {
        context.debugTargetQuit(event.command);
    }

    @Subscribe
    public void debugeeCallStackFormed(Debugee.CmdCallStackFormedEvent event) {
        context.debugCallStackFormed(event.command);
    }

    @Subscribe
    public void debugeeExprEvaluated(Debugee.CmdExprEvaluated event) {
        context.debugExprEvaluated(event.command);
    }
}
