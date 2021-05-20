package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.AttachDebugUIResult;
import lombok.Data;

@Data
public class RDBGAttachDebugUIResponse implements IRDBGResponse {
    private AttachDebugUIResult result;
}
