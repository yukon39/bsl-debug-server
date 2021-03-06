package com.github.yukon39.bsl.debugserver.events;

import com.github.yukon39.bsl.debugserver.BSLDebugServer;
import com.github.yukon39.bsl.debugserver.context.ServerContext;
import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.google.common.eventbus.Subscribe;
import org.eclipse.lsp4j.debug.OutputEventArguments;
import org.eclipse.lsp4j.debug.OutputEventArgumentsCategory;
import org.eclipse.lsp4j.debug.TerminatedEventArguments;
import org.eclipse.lsp4j.debug.services.IDebugProtocolClient;

public class DebugClientEventSubscribers {

    private final IDebugProtocolClient client;

    public DebugClientEventSubscribers(IDebugProtocolClient client) {
        this.client = client;
    }

    @Subscribe
    public void serverOutputErrorHandler(BSLDebugServer.OutputErrorEvent event) {
        this.output(event.output, OutputEventArgumentsCategory.STDERR);
    }

    @Subscribe
    public void serverTerminatedErrorHandler(BSLDebugServer.TerminatedErrorEvent event) {
        client.terminated(new TerminatedEventArguments());
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
        output(event.output);
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
