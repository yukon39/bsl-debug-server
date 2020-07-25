package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationResultBaseData;
import lombok.Data;

@Data
public class RDBGEvalExprResponse implements IRDBGResponse {
    private CalculationResultBaseData[] result;
}
