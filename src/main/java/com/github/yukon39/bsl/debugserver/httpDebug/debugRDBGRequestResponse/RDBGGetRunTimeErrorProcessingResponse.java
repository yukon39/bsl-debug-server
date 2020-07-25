package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugRTEFilter.RteFilterStorage;
import lombok.Data;

@Data
public class RDBGGetRunTimeErrorProcessingResponse implements IRDBGResponse {
    private RteFilterStorage state;
}
