package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DbgTargetState;
import lombok.Data;

@Data
public class RDBGGetDbgTargetStateResponse implements IRDBGResponse {
    private DbgTargetState state;
}
