package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdLight;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGGetDbgTargetStateRequest extends RDbgBaseRequest implements IRDBGRequest {
    private DebugTargetIdLight id;
}
