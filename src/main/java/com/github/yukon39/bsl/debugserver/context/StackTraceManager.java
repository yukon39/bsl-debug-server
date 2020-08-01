package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import lombok.Setter;
import org.eclipse.lsp4j.debug.Source;
import org.eclipse.lsp4j.debug.StackFrame;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class StackTraceManager {

    @Setter
    private SourceManager sourceManager;

    private final Map<Integer, List<StackFrame>> stackFrames = new HashMap<>();

    public void setStackTrace(Integer threadId, List<StackItemViewInfoData> stackItems) {

        if (Objects.isNull(stackItems)) {
            return;
        }

        var frames = new ArrayList<StackFrame>();

        stackItems.forEach(stackItem -> {

            var presentation = new String(stackItem.getPresentation(), StandardCharsets.UTF_8);

            var source = sourceManager.getSource(stackItem.getModuleID());
            if(Objects.isNull(source.getAdapterData())) {
                source.setAdapterData(stackItem.getModuleID());
            }

            var stackFrame = new StackFrame();
            stackFrame.setLine(stackItem.getLineNo());
            stackFrame.setName(presentation);

            stackFrame.setSource(source);

            frames.add(stackFrame);
        });

        stackFrames.put(threadId, frames);
    }

    public List<StackFrame> getStackTrace(Integer threadId) {
        if (stackFrames.containsKey(threadId)) {
            return stackFrames.get(threadId);
        } else {
            return new ArrayList<StackFrame>();
        }
    }
}
