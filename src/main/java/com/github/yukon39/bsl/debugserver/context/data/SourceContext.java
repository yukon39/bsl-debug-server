package com.github.yukon39.bsl.debugserver.context.data;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import lombok.Data;
import org.eclipse.lsp4j.debug.Source;
import org.jetbrains.annotations.NotNull;

@Data
public class SourceContext {

    @NotNull
    private final BSLModuleIdInternal moduleId;

    @NotNull
    private final Source source;
}
