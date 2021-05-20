package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetInaccessibleModulesRequest extends RDbgBaseRequest implements IRDBGRequest {
    private final List<BSLModuleIdInternal> moduleID = new ArrayList<>();
}
