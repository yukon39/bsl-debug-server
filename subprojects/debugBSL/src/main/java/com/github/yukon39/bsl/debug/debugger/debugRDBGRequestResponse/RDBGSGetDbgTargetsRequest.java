package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSGetDbgTargetsRequest extends RDbgBaseRequest {
    private String degugAreaName; // yes, degug
}
