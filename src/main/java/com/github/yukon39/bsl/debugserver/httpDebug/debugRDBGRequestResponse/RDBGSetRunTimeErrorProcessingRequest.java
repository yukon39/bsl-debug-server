package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugRTEFilter.RteFilterStorage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetRunTimeErrorProcessingRequest extends RDbgBaseRequest {
    private RteFilterStorage state;
}
