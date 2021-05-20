package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import lombok.Data;

@Data
public class RDBGGetDebugTargetForegroundHelperResponse implements IRDBGResponse {
    private ForegroundWindowData data;
}
