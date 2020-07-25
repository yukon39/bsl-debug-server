package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

@Data
public class SourceCalculationDataItem {
    private SourceCalculationDataItemType itemType;
    private String expression;
    private String property;
    private Integer index;
}
