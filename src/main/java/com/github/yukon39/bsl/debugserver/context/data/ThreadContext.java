package com.github.yukon39.bsl.debugserver.context.data;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import lombok.Data;
import org.eclipse.lsp4j.debug.Thread;
import org.jetbrains.annotations.NotNull;

@Data
public class ThreadContext {

    @NotNull
    private final Thread thread;

    @NotNull
    private final DebugTargetId targetId;
}
