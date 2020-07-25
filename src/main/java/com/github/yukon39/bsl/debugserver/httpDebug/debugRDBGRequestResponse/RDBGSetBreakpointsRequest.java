package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetBreakpointsRequest extends RDbgBaseRequest {
    private BPWorkspaceInternal bpWorkspace;
}
