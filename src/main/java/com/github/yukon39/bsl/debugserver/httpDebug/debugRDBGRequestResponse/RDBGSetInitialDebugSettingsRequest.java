package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetInitialDebugSettingsRequest extends RDbgBaseRequest implements IRDBGRequest {
    private HTTPServerInitialDebugSettingsData data;
}
