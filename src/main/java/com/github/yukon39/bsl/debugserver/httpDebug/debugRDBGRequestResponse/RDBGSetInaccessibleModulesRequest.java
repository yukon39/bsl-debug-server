package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetInaccessibleModulesRequest extends RDbgBaseRequest implements IRDBGRequest {
    private final List<BSLModuleIdInternal> moduleID = new ArrayList<>();
}
