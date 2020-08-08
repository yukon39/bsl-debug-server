package com.github.yukon39.bsl.debugserver.context.data;

import lombok.Data;
import org.eclipse.lsp4j.debug.StackFrame;
import org.eclipse.lsp4j.debug.Thread;

@Data
public class StackFrameContext {

    private final Thread thread;
    private final Integer stackLevel;
    private final StackFrame stackFrame;

    public StackFrameContext(Thread thread, Integer stackLevel, StackFrame stackFrame) {
        this.thread = thread;
        this.stackLevel = stackLevel;
        this.stackFrame = stackFrame;
    }
}
