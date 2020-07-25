package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

@Data
public class RDBGGetInaccessibleModulesResponse implements IRDBGResponse {
    private BSLModuleIdInternal[] moduleID;
}
