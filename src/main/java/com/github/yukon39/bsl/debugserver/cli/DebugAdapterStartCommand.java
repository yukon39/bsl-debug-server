package com.github.yukon39.bsl.debugserver.cli;

import com.github.yukon39.bsl.debugserver.BSLDebugServer;
import com.github.yukon39.bsl.debugserver.events.DebugClientEventSubscribers;
import com.google.common.eventbus.EventBus;
import org.eclipse.lsp4j.debug.DisconnectArguments;
import org.eclipse.lsp4j.debug.launch.DSPLauncher;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
        name = "dap",
        aliases = {"--dap"}
)
public class DebugAdapterStartCommand implements Callable<Integer> {

    @Override
    public Integer call() {

        var server = new BSLDebugServer();

        var launcher = DSPLauncher.createServerLauncher(server, System.in, System.out);

        var clientProxy = launcher.getRemoteProxy();

        var eventBus = new EventBus();

        eventBus.register(new DebugClientEventSubscribers(clientProxy));
        server.setEventBus(eventBus);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown(server);
            }
        });

        launcher.startListening();

        return -1;
    }

    public void shutdown(@NotNull BSLDebugServer server) {
        var args = new DisconnectArguments();
        server.disconnect(args);
    }
}
