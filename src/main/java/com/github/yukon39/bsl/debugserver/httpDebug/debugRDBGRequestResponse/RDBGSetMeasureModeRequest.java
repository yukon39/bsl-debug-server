package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetMeasureModeRequest extends RDbgBaseRequest {
    private UUID measureModeSeanceID;
}
