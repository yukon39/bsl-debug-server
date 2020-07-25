package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationSourceDataStorage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGEvalLocalVariablesRequest extends RDbgBaseRequest {
    private Integer calcWaitingTime;
    private DebugTargetIdLight targetID;
    private CalculationSourceDataStorage[] expr;
}
