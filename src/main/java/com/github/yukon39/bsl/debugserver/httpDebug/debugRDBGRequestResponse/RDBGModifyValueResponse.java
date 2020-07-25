package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationResultBaseData;
import lombok.Data;

@Data
public class RDBGModifyValueResponse implements IRDBGResponse {
    private Boolean processed;
    private CalculationResultBaseData newValueState;
    private char[] errorDescr;
}
