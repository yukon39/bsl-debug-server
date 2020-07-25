package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import lombok.Data;

@Data
public class RDBGSGetDbgTargetsResponse implements IRDBGResponse {
    private DebugTargetId[] id;
}
