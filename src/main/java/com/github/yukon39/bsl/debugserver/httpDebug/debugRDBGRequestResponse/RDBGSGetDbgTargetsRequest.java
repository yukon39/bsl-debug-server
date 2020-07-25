package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSGetDbgTargetsRequest extends RDbgBaseRequest {
    private String degugAreaName; // yes, degug
}
