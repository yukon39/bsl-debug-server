package com.github.yukon39.bsl.debugserver.cli;

import com.github.yukon39.bsl.debugserver.BSLDebugServer;
import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.github.yukon39.bsl.debugserver.events.DebugClientEventSubscribers;
import com.github.yukon39.bsl.debugserver.events.DebugServerEventSubscribers;
import com.google.common.eventbus.EventBus;
import org.eclipse.lsp4j.debug.DisconnectArguments;
import org.eclipse.lsp4j.debug.launch.DSPLauncher;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static picocli.CommandLine.Command;

@Command(
        name = "dap",
        aliases = {"--dap"}
)
public class DebugAdapterStartCommand implements Callable<Integer> {

    public Integer call() {

        var server = new BSLDebugServer();
        var in = System.in;
        var out = System.out;

        var launcher = DSPLauncher.createServerLauncher(server, in, out);

        var clientProxy = launcher.getRemoteProxy();


        var eventBus = new EventBus();

        eventBus.register(new DebugClientEventSubscribers(clientProxy));
        server.setEventBus(eventBus);

        Runtime.getRuntime().addShutdownHook(new Thread() { public void run() { shutdown (server); } });

        launcher.startListening();

        return -1;
    }

    public void shutdown(@NotNull BSLDebugServer server) {
        var args = new DisconnectArguments();
        server.disconnect(args);
    }
}
