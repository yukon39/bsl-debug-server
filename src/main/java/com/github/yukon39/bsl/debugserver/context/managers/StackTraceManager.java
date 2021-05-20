package com.github.yukon39.bsl.debugserver.context.managers;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
import com.github.yukon39.bsl.debugserver.context.data.StackFrameContext;
import com.github.yukon39.bsl.debugserver.context.data.ThreadContext;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.eclipse.lsp4j.debug.StackFrame;
import org.eclipse.lsp4j.debug.StackFramePresentationHint;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StackTraceManager {

    private final Map<Integer, List<StackFrame>> stackFrames = new HashMap<>();
    private final Map<Integer, StackFrameContext> stackFrameContexts = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    @Setter
    private SourceManager sourceManager;

    public void setStackTrace(ThreadContext threadContext, List<StackItemViewInfoData> stackItems) {

        if (stackItems.isEmpty()) {
            return;
        }

        var thread = threadContext.getThread();
        var frames = new ArrayList<StackFrame>();

        AtomicInteger stackLevel = new AtomicInteger(0);

        Lists.reverse(stackItems).forEach(stackItem -> {

            var sourceContext = sourceManager.getSourceContext(stackItem.getModuleID());
            var source = sourceContext.getSource();

            var stackFrame = new StackFrame();
            stackFrame.setId(counter.getAndIncrement());
            stackFrame.setLine(stackItem.getLineNo());
            stackFrame.setName(stackItem.getUserPresentation());
            stackFrame.setSource(source);
            stackFrame.setPresentationHint(StackFramePresentationHint.NORMAL);
            frames.add(stackFrame);

            var stackFrameContext = new StackFrameContext(threadContext,
                    stackLevel.getAndIncrement(),
                    stackFrame);

            stackFrameContexts.put(stackFrame.getId(), stackFrameContext);
        });

        stackFrames.put(thread.getId(), frames);
    }

    public @Nullable StackFrameContext getStackFrameContext(Integer stackFrameId) {
        return stackFrameContexts.get(stackFrameId);
    }

    public List<StackFrame> getStackTrace(Integer threadId) {
        if (stackFrames.containsKey(threadId)) {
            return stackFrames.get(threadId);
        } else {
            return new ArrayList<StackFrame>();
        }
    }
}
