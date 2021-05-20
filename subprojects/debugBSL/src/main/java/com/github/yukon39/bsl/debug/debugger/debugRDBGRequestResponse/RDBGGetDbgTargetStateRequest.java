package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGGetDbgTargetStateRequest extends RDbgBaseRequest implements IRDBGRequest {
    private DebugTargetIdLight id;
}
