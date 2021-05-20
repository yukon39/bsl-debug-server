package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGGetForegroundHelperDataRequest extends RDbgBaseRequest {
    private DebugTargetId targetID;
}
