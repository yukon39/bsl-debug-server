package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import lombok.Data;

@Data
public class RDBGGetRunTimeErrorProcessingResponse implements IRDBGResponse {
    private RteFilterStorage state;
}
