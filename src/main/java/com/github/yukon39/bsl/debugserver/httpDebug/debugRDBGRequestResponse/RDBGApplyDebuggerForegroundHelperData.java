package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debugserver.debugee.debugForegroundData.ForegroundWindowData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGApplyDebuggerForegroundHelperData extends RDbgBaseRequest {
    private DebugTargetId[] targetID;
    private ForegroundWindowData data;
}
