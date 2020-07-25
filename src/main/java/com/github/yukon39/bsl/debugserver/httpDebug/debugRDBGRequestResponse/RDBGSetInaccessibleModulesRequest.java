package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetInaccessibleModulesRequest extends RDbgBaseRequest {
    private BSLModuleIdInternal[] moduleID;
}
