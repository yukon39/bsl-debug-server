package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

@Data
public class CalculationResultEnumAndColPropertyInfo {
    private BaseValueInfoData valueInfo;
    private CalculationResultContextPropertyInfo[] valueOfContextPropInfo;
}
