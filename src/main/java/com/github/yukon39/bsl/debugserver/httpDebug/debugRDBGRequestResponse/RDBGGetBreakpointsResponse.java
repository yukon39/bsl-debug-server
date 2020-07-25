package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import lombok.Data;

@Data
public class RDBGGetBreakpointsResponse implements IRDBGResponse {
    private BPWorkspaceInternal bpWorkspace;
}
