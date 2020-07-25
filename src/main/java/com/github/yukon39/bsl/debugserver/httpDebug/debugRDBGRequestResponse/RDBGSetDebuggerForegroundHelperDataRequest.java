package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugForegroundData.ForegroundWindowData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetDebuggerForegroundHelperDataRequest extends RDbgBaseRequest {
    private UUID receiverID;
    private ForegroundWindowData data;
}
