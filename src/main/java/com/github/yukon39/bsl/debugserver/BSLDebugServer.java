package com.github.yukon39.bsl.debugserver;

import com.github.yukon39.bsl.debugserver.configuration.DebugServerConfiguration;
import com.github.yukon39.bsl.debugserver.context.AttachRequestArguments;
import com.github.yukon39.bsl.debugserver.context.ServerContext;
import com.github.yukon39.bsl.debugserver.events.DebugServerEventSubscribers;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.lsp4j.debug.*;
import org.eclipse.lsp4j.debug.services.IDebugProtocolServer;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BSLDebugServer implements IDebugProtocolServer {

    private final DebugServerConfiguration configuration;
    private final ServerContext context;

    private EventBus eventBus;

    public BSLDebugServer(DebugServerConfiguration configuration) {
        this.context = new ServerContext();
        this.configuration = configuration;
    }

    public BSLDebugServer() {
        this(DebugServerConfiguration.create());
    }

    @Override
    public CompletableFuture<Capabilities> initialize(InitializeRequestArguments args) {

        // Values from args overrides configuration's values
//        String locale = args.getLocale();
//        if (locale != null && !locale.isBlank()) {
//            var language = Language.valueOf(locale);
//            configuration.setLanguage(language);
//        }

        String pathFormat = args.getPathFormat();
        if (pathFormat != null && !pathFormat.isBlank()) {
            configuration.setPathFormat(pathFormat);
        }

        Boolean linesStartAt1 = args.getLinesStartAt1();
        if (linesStartAt1 != null) {
            configuration.setLinesStartAt1(linesStartAt1);
        }

        Boolean columnsStartAt1 = args.getColumnsStartAt1();
        if (columnsStartAt1 != null) {
            configuration.setColumnsStartAt1(columnsStartAt1);
        }

        Boolean supportsVariableType = args.getSupportsVariableType();
        if (supportsVariableType != null) {
            configuration.setSupportsVariableType(supportsVariableType);
        }

        Boolean supportsVariablePaging = args.getSupportsVariablePaging();
        if (supportsVariablePaging != null) {
            configuration.setSupportsVariablePaging(supportsVariablePaging);
        }

        Boolean supportsRunInTerminalRequest = args.getSupportsRunInTerminalRequest();
        if (supportsRunInTerminalRequest != null) {
            configuration.setSupportsRunInTerminalRequest(supportsRunInTerminalRequest);
        }

        context.initialize();

        Capabilities capabilities = new Capabilities();
        capabilities.setSupportsRestartRequest(true);
        capabilities.setSupportsConfigurationDoneRequest(true);

        return CompletableFuture.completedFuture(capabilities);
    };

    @Override
    public CompletableFuture<Void> attach(Map<String, Object> args) {

        var requestArgs = new AttachRequestArguments(args);

        try {
            requestArgs.validate(configuration.getLanguage());
            context.attach(requestArgs);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            log.error("Attach error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<Void> restart(RestartArguments args) {

        try {
            context.restart(args);
        } catch (Exception e) {
            log.error("Restart error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<Void> disconnect(DisconnectArguments args) {

        try {
            context.disconnect(args);
        } catch (Exception e) {
            log.error("Disconnect error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<SetBreakpointsResponse> setBreakpoints(SetBreakpointsArguments args) {

        var response = new SetBreakpointsResponse();
        response.setBreakpoints(new Breakpoint[] {} );

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<Void> configurationDone(ConfigurationDoneArguments args) {
        try {
            context.configurationDone(args);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            log.error("Configuration done error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<ThreadsResponse> threads() {

        try {
            var response = context.threads();
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            log.error("Threads error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<Void> pause(PauseArguments args) {

        try {
            context.pause();
        } catch (Exception e) {
            log.error("Pause error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public CompletableFuture<ContinueResponse> continue_(ContinueArguments args) {
        try {
            var response = context.stepContinue(args);
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            log.error("Step continue error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<Void> stepIn(StepInArguments args) {
        try {
            context.stepIn(args);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            log.error("Step in error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<Void> stepOut(StepOutArguments args) {
        try {
            context.stepOut(args);
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            log.error("Step out error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<StackTraceResponse> stackTrace(StackTraceArguments args) {

        try {
            var result = context.stackTrace(args);
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            log.error("Stacktrace error: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

    private void postEvent(Object event) {
        if (eventBus != null) {
            eventBus.post(event);
        }
    }

    public class InitializeEvent {
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        this.context.setEventBus(eventBus);
        eventBus.register(new DebugServerEventSubscribers(this));
    }

    public class OutputErrorEvent {
        public final String output;

        public OutputErrorEvent(String output) {
            this.output = output;
        }
    }

}
