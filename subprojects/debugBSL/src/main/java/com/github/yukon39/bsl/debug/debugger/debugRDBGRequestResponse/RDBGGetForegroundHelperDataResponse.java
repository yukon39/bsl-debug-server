package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import lombok.Data;

@Data
public class RDBGGetForegroundHelperDataResponse implements IRDBGResponse {
    private ForegroundWindowData data;
}
