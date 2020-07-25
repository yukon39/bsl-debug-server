package com.github.yukon39.bsl.debugserver;

import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import org.eclipse.lsp4j.debug.Capabilities;
import org.eclipse.lsp4j.debug.DisconnectArguments;
import org.eclipse.lsp4j.debug.InitializeRequestArguments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class BSLDebugServerTest {

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
    void initialize() {

        // given
        InitializeRequestArguments params = new InitializeRequestArguments();

        // when
        CompletableFuture<Capabilities> initialize = server.initialize(params);

        // then
        Capabilities capabilities = initialize.getNow(null);
        assertThat(capabilities).isNotNull();
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
}
