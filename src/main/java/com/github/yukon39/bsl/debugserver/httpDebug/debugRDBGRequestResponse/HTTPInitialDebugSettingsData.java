package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugRTEFilter.RteFilterStorage;
import lombok.Data;

import java.util.UUID;

@Data
public class HTTPInitialDebugSettingsData {
    private UUID envStateVersion;
    private Boolean breakOnNextLine;
    private UUID measureMode;
    private Integer serverIndependentWorkTime;
    private BPWorkspaceInternal bpWorkspace;
    private UUID bpVersion;
    private RteFilterStorage rteProcessing;
    private UUID rteProcVersion;
    private BSLModuleIdInternal[] inacessibleModuleID;
    private UUID inacessibleModuleVersion;
}
