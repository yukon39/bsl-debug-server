package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.NewValueInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGModifyValueRequest extends RDbgBaseRequest {
    private DebugTargetIdLight targetID;
    private CalculationSourceDataStorage modifyDataPath;
    private NewValueInfo newValueInfo;
    private Integer timeout;
}
