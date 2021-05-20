package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import lombok.Data;

@Data
public class RDBGGetBreakpointsResponse implements IRDBGResponse {
    private BPWorkspaceInternal bpWorkspace;
}
