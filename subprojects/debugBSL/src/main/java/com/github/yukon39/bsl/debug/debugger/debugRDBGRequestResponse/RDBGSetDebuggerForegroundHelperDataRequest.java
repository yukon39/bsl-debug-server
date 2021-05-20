package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetDebuggerForegroundHelperDataRequest extends RDbgBaseRequest {
    private UUID receiverID;
    private ForegroundWindowData data;
}
