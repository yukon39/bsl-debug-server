package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import lombok.Data;

@Data
public class MiscRDbgGetAPIVerResponse implements IRDBGResponse {
    private String version;
}
