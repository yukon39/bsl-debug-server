package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import lombok.Data;

@Data
public class RDBGEvalLocalVariablesResponse implements IRDBGResponse {
    private CalculationResultBaseData result;
}
