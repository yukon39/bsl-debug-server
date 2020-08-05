package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.AttachDebugUIResult;
import lombok.Data;

@Data
public class RDBGAttachDebugUIResponse implements IRDBGResponse {
    private AttachDebugUIResult result;
}
