package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetRunTimeErrorProcessingRequest extends RDbgBaseRequest {
    private RteFilterStorage state;
}
