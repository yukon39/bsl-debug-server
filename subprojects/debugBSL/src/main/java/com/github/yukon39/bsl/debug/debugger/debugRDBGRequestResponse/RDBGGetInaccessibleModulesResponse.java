package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RDBGGetInaccessibleModulesResponse implements IRDBGResponse {
    private final List<BSLModuleIdInternal> moduleID = new ArrayList<>();
}
