package com.github.yukon39.bsl.debugserver;

import com.github.yukon39.bsl.debugserver.configuration.DebugServerConfiguration;
import com.github.yukon39.bsl.debugserver.context.AttachRequestArguments;
import com.github.yukon39.bsl.debugserver.context.ServerContext;
import com.github.yukon39.bsl.debugserver.events.DebugServerEventSubscribers;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.lsp4j.debug.*;
import org.eclipse.lsp4j.debug.services.IDebugProtocolServer;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

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

        var capabilities = new Capabilities();
        capabilities.setSupportsRestartRequest(Boolean.TRUE);
        capabilities.setSupportsConfigurationDoneRequest(Boolean.TRUE);

        return context.initialize(configuration)
                .thenApplyAsync(s -> capabilities)
                .exceptionally((e) ->
                        {
                            log.error("Initialize error", e);
                            outputError("Initialize error", e); // TODO: localize this
                            postEvent(new TerminatedErrorEvent());
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<Void> configurationDone(ConfigurationDoneArguments args) {

        return context.configurationDone(args)
                .exceptionally((e) ->
                        {
                            log.error("ConfigurationDone error", e);
                            outputError("ConfigurationDone error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<Void> attach(Map<String, Object> args) {

        var requestArgs = new AttachRequestArguments(args);
        return context.attach(requestArgs)
                .exceptionally((e) ->
                        {
                            log.error("Attach error", e);
                            outputError("Attach error", e); // TODO: localize this
                            postEvent(new TerminatedErrorEvent());
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<Void> restart(RestartArguments args) {

        return context.restart(args)
                .exceptionally((e) ->
                        {
                            log.error("Restart error", e);
                            outputError("Restart error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<Void> disconnect(DisconnectArguments args) {

        return context.disconnect(args)
                .exceptionally((e) ->
                        {
                            log.error("Disconnect error", e);
                            outputError("Disconnect error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<SetBreakpointsResponse> setBreakpoints(SetBreakpointsArguments args) {

        return context.setBreakpoints(args)
                .exceptionally((e) ->
                        {
                            log.error("SetBreakpoints error", e);
                            outputError("SetBreakpoints error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<ContinueResponse> continue_(ContinueArguments args) {

        return context.stepContinue(args)
                .exceptionally((e) ->
                        {
                            log.error("Step continue error", e);
                            outputError("Step continue error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<Void> stepIn(StepInArguments args) {

        return context.stepIn(args)
                .exceptionally((e) ->
                        {
                            log.error("Step in error", e);
                            outputError("Step in error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<Void> stepOut(StepOutArguments args) {

        return context.stepOut(args)
                .exceptionally((e) ->
                        {
                            log.error("Step out error", e);
                            outputError("Step out error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<Void> pause(PauseArguments args) {

        return context.pause()
                .exceptionally((e) ->
                        {
                            log.error("Pause error", e);
                            outputError("Pause error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<StackTraceResponse> stackTrace(StackTraceArguments args) {

        return context.stackTrace(args)
                .exceptionally((e) ->
                        {
                            log.error("Stacktrace error", e);
                            outputError("Stacktrace error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<ScopesResponse> scopes(ScopesArguments args) {

        return context.scopes(args)
                .exceptionally((e) ->
                        {
                            log.error("Scopes error", e);
                            outputError("Scopes error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<SourceResponse> source(SourceArguments args) {

        return context.source(args)
                .exceptionally((e) ->
                        {
                            log.error("Source error", e);
                            outputError("Source error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<VariablesResponse> variables(VariablesArguments args) {
        return context.variables(args)
                .exceptionally((e) ->
                        {
                            log.error("Variables error", e);
                            outputError("Variables error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    @Override
    public CompletableFuture<ThreadsResponse> threads() {

        return context.threads()
                .exceptionally((e) ->
                        {
                            log.error("Threads error", e);
                            outputError("Threads error", e); // TODO: localize this
                            return null;
                        }
                );
    }

    private void outputError(String msgPrefix, Throwable throwable) {

        var sw = new StringWriter();
        var pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);

        String msg = String.format("%s: %s\n%s",
                msgPrefix,
                throwable.getLocalizedMessage(),
                sw.toString());
        postEvent(new OutputErrorEvent(msg));
    }

    private void postEvent(Object event) {
        if (Objects.nonNull(eventBus)) {
            eventBus.post(event);
        }
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        this.context.setEventBus(eventBus);
        eventBus.register(new DebugServerEventSubscribers(this));
    }

    public static class OutputErrorEvent {
        public final String output;

        public OutputErrorEvent(String output) {
            this.output = output;
        }
    }

    public static class TerminatedErrorEvent {
    }
}
