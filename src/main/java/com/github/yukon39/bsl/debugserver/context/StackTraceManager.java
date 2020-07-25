package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.Debugee;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import com.google.common.eventbus.Subscribe;
import org.eclipse.lsp4j.debug.StackFrame;
import org.eclipse.lsp4j.debug.StoppedEventArgumentsReason;
import org.eclipse.lsp4j.debug.Thread;
import org.jetbrains.annotations.Debug;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class StackTraceManager {

    private final ServerContext serverContext;

    private final Map<Integer, List<StackFrame>> stackFrames = new HashMap<>();

    public StackTraceManager(ServerContext serverContext) {
        this.serverContext = serverContext;
    }

    public void setStackTrace(Integer threadId, List<StackItemViewInfoData> stackItems) {

        if(stackItems == null) {
            return;
        }

        var frames = new ArrayList<StackFrame>();

        stackItems.forEach(stackItem -> {

            var presentation = new String(stackItem.getPresentation(), StandardCharsets.UTF_8);

            var stackFrame = new StackFrame();
            stackFrame.setLine(stackItem.getLineNo());
            stackFrame.setName(presentation);

            frames.add(stackFrame);
        });

        stackFrames.put(threadId, frames);
    }

    public List<StackFrame> getStackTrace(Integer threadId) {
        if(stackFrames.containsKey(threadId)) {
            return stackFrames.get(threadId);
        } else {
            return new ArrayList<StackFrame>();
        }
    }
}
