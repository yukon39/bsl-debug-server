package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import lombok.Data;

@Data
public class MiscRDbgGetAPIVerResponse implements IRDBGResponse {
    private String version;
}
