package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

@Data
public class CalculationSourceDataStorage {
    private Integer stackLevel;
    private SourceCalculationDataInfo srcCalcInfo;
    private DbgPresentationOptionsOfStringValue presOptions;
}
