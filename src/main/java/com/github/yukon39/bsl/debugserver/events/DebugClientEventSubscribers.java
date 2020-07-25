package com.github.yukon39.bsl.debugserver.events;

import com.github.yukon39.bsl.debugserver.BSLDebugServer;
import com.github.yukon39.bsl.debugserver.context.ServerContext;
import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.google.common.eventbus.Subscribe;
import org.eclipse.lsp4j.debug.*;
import org.eclipse.lsp4j.debug.services.IDebugProtocolClient;

public class DebugClientEventSubscribers {

    private final IDebugProtocolClient client;

    public DebugClientEventSubscribers(IDebugProtocolClient client) {
        this.client = client;
    }

    @Subscribe
    public void contextInitializeHandler(ServerContext.InitializeEvent event) {
        client.initialized();
    }

    @Subscribe
    public void contextThreadHandler(ServerContext.ThreadEvent event) {
        client.thread(event.args);
    }

    @Subscribe
    public void contextStoppedHandler(ServerContext.StoppedEvent event) {
        client.stopped(event.args);
    }

    @Subscribe
    public void debugeeOutputHandler(Debugee.OutputEvent event) {
        var args = new OutputEventArguments();
        args.setOutput(event.output + System.lineSeparator());
        client.output(args);
    }

    @Subscribe
    public void outputErrorEvent(BSLDebugServer.OutputErrorEvent event) {
        output(event.output, OutputEventArgumentsCategory.STDERR);
    }

    private void output(String output, String category) {
        var args = new OutputEventArguments();
        args.setCategory(category);
        args.setOutput(output + System.lineSeparator());
        client.output(args);
    }

    private void output(String output) {
        output(output, OutputEventArgumentsCategory.CONSOLE);
    }

}
