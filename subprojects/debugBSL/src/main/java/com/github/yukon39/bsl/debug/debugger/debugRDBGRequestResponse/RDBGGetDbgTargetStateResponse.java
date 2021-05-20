package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DbgTargetState;
import lombok.Data;

@Data
public class RDBGGetDbgTargetStateResponse implements IRDBGResponse {
    private DbgTargetState state;
}
