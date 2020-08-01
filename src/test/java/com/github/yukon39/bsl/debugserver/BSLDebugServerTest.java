package com.github.yukon39.bsl.debugserver;

import org.eclipse.lsp4j.debug.Capabilities;
import org.eclipse.lsp4j.debug.DisconnectArguments;
import org.eclipse.lsp4j.debug.InitializeRequestArguments;
import org.eclipse.lsp4j.debug.launch.DSPLauncher;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.lsp4j.jsonrpc.json.MessageConstants.CONTENT_LENGTH_HEADER;
import static org.eclipse.lsp4j.jsonrpc.json.MessageConstants.CRLF;

public class BSLDebugServerTest {

    private static final long TIMEOUT = 2000;
    private BSLDebugServer server;

    @BeforeEach
    void setup() throws IOException {
        server = new BSLDebugServer();
    }

    @AfterEach
    void close() {
        var args = new DisconnectArguments();
        args.setRestart(false);
        args.setTerminateDebuggee(false);
        server.disconnect(args);
    }

    @Test
    void initialize() throws ExecutionException, InterruptedException {

        // given
        InitializeRequestArguments params = new InitializeRequestArguments();

        // when
        CompletableFuture<Capabilities> initialize = server.initialize(params);

        // then
        Capabilities capabilities = initialize.get();
        assertThat(capabilities.getSupportsRestartRequest()).isTrue();
        assertThat(capabilities.getSupportsConfigurationDoneRequest()).isTrue();
    }

    @Test
    void attach() {

        // given
        var args = new HashMap<String, Object>();
        args.put("debuggerURL", "http://localhost:8888");
        args.put("infobaseAlias", "DefAlias");

        // when
        InitializeRequestArguments params = new InitializeRequestArguments();
        params.setLocale("EN");
        server.initialize(params);

        CompletableFuture<Void> attach = server.attach(args);

        // then
        var result = attach.getNow(null);
        assertThat(result).isNull();


    }

    @Test
    void run() throws IOException, InterruptedException, ExecutionException, TimeoutException {

        var file = new File("./src/test/resources/json/1_initialize.json");
        var initializeRequest = Files.readString(file.toPath());

        file = new File("./src/test/resources/json/2_attach.json");
        var attachRequest = Files.readString(file.toPath());

        file = new File("./src/test/resources/json/3_setBreakpoints.json");
        var setBreakpointsRequest = Files.readString(file.toPath());

        file = new File("./src/test/resources/json/4_configurationDone.json");
        var configurationDoneRequest = Files.readString(file.toPath());


        file = new File("./src/test/resources/json/90-disconnect.json");
        var disconnectRequest = Files.readString(file.toPath());

        var clientMessages = getHeader(initializeRequest.length()) + initializeRequest
                + getHeader(attachRequest.length()) + attachRequest
                + getHeader(setBreakpointsRequest.length()) + setBreakpointsRequest
                + getHeader(configurationDoneRequest.length()) + configurationDoneRequest

                + getHeader(disconnectRequest.length()) + disconnectRequest;

        var inServer = new ByteArrayInputStream(clientMessages.getBytes());
        var outServer = new ByteArrayOutputStream();

        var serverLauncher = DSPLauncher.createServerLauncher(server, inServer, outServer);

        serverLauncher.startListening().get(TIMEOUT, TimeUnit.SECONDS);

        var response = outServer.toString();
    }

    protected String getHeader(int contentLength) {
        StringBuilder headerBuilder = new StringBuilder();
        headerBuilder.append(CONTENT_LENGTH_HEADER).append(": ").append(contentLength).append(CRLF);
        headerBuilder.append(CRLF);
        return headerBuilder.toString();
    }
}
