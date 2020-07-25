package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import lombok.Data;

@Data
public class RDBGAttachDetachDebugTargetsResponse implements IRDBGResponse {
    private Boolean result;
}
