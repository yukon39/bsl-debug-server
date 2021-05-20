package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGNotifyOnFinishSeanceRequest extends RDbgBaseRequest {
    private UUID[] seanceID;
}
