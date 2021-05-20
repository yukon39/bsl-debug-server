package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGApplyDebuggerForegroundHelperData extends RDbgBaseRequest {
    private DebugTargetId[] targetID;
    private ForegroundWindowData data;
}
