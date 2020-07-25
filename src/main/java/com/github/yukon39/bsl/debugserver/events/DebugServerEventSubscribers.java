package com.github.yukon39.bsl.debugserver.events;

import com.github.yukon39.bsl.debugserver.context.ServerContext;
import org.eclipse.lsp4j.debug.services.IDebugProtocolServer;

public class DebugServerEventSubscribers {
    private final IDebugProtocolServer server;

    public DebugServerEventSubscribers(IDebugProtocolServer server) {
        this.server = server;
    }
}
