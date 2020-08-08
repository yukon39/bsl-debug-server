package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.context.managers.SourceManager;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import lombok.Setter;
import org.eclipse.lsp4j.debug.StackFrame;
import org.eclipse.lsp4j.debug.StackFramePresentationHint;
import org.eclipse.lsp4j.debug.Thread;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StackTraceManager {

    private final Map<Integer, List<StackFrame>> stackFrames = new HashMap<>();
    private final Map<Integer, StackFrameContext> stackFrameContexts = new HashMap<>();

    @Setter
    private SourceManager sourceManager;
    private AtomicInteger counter = new AtomicInteger(0);

    public void setStackTrace(Thread thread, @Nullable List<StackItemViewInfoData> stackItems) {

        if (Objects.isNull(stackItems) || stackItems.isEmpty()) {
            return;
        }

        var frames = new ArrayList<StackFrame>();

        AtomicInteger stackLevel = new AtomicInteger(0);

        stackItems.forEach(stackItem -> {

            var presentation = new String(stackItem.getPresentation(), StandardCharsets.UTF_8);

            var source = sourceManager.getSource(stackItem.getModuleID());
            if (Objects.isNull(source.getAdapterData())) {
                source.setAdapterData(stackItem.getModuleID());
            }

            var stackFrame = new StackFrame();
            stackFrame.setId(counter.getAndIncrement());
            stackFrame.setLine(stackItem.getLineNo());
            stackFrame.setName(presentation);
            stackFrame.setSource(source);
            stackFrame.setPresentationHint(StackFramePresentationHint.NORMAL);
            frames.add(stackFrame);

            var stackFrameContext = new StackFrameContext(thread,
                    stackLevel.getAndIncrement(),
                    stackFrame);

            stackFrameContexts.put(stackFrame.getId(), stackFrameContext);
        });

        stackFrames.put((int) thread.getId(), frames);
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
