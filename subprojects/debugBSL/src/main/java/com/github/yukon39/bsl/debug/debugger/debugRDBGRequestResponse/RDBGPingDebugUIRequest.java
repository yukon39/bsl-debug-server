package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGPingDebugUIRequest extends RDbgBaseRequest implements IRDBGRequest {
    private Boolean saveDataAsString;
}
