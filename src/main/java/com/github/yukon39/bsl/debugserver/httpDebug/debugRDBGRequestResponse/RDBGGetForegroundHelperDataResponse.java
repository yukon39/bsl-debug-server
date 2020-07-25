package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugForegroundData.ForegroundWindowData;
import lombok.Data;

@Data
public class RDBGGetForegroundHelperDataResponse implements IRDBGResponse {
    private ForegroundWindowData data;
}
