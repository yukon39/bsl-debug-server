package com.github.yukon39.bsl.debugserver.context.data;

import lombok.Data;
import org.eclipse.lsp4j.debug.Variable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class VariablesContext {

    private static AtomicInteger counter = new AtomicInteger(0);

    private final Integer reference;

    private final List<Variable> variables = new ArrayList<>();
    private final Set<VariablesContext> subContext = new HashSet<>();

    private final UUID resultId;

    private boolean isProcessed = false;
    private boolean isReceived = false;
    private StackFrameContext stackFrameContext;

    private boolean isExpandable = false;
    private boolean isContextVariable = false;
    private boolean isEnumVariable = false;
    private boolean isCollection = false;

    private String variableName = "";

    public VariablesContext() {
        this.reference = counter.incrementAndGet();
        this.resultId = UUID.randomUUID();
    }
}
