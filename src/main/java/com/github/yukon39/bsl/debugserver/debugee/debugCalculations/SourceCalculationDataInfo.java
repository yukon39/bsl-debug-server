package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

import java.util.UUID;

@Data
public class SourceCalculationDataInfo {
    private UUID expressionID;
    private UUID expressionResultID;
    private SourceCalculationDataItem[] calcItem;
    private ViewInterface[] interfaces;
}
