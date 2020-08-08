package com.github.yukon39.bsl.debugserver.context.data;

import com.github.yukon39.bsl.debugserver.context.StackFrameContext;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationResultBaseData;
import lombok.Data;
import org.eclipse.lsp4j.debug.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class VariablesContext {

    private static AtomicInteger counter = new AtomicInteger(0);

    private final Integer reference;

    private final List<Variable> variables = new ArrayList<>();

    private final UUID resultId;

    private boolean isProcessed = false;
    private boolean isReceived = false;
    private StackFrameContext stackFrameContext;

    public VariablesContext() {
        this.reference = counter.incrementAndGet();
        this.resultId = UUID.randomUUID();
    }
}
