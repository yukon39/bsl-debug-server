package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RDBGGetInaccessibleModulesRequest extends RDbgBaseRequest implements IRDBGRequest {
}
