package com.github.yukon39.bsl.debugserver.context.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.eclipse.lsp4j.debug.StackFrame;
import org.eclipse.lsp4j.debug.Thread;

@Data
@AllArgsConstructor
public class StackFrameContext {

    private final ThreadContext threadContext;
    private final Integer stackLevel;
    private final StackFrame stackFrame;

}
