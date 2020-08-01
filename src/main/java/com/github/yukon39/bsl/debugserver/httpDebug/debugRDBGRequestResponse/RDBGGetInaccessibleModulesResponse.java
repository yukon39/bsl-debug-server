package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RDBGGetInaccessibleModulesResponse implements IRDBGResponse {
    private final List<BSLModuleIdInternal> moduleID = new ArrayList<>();
}
