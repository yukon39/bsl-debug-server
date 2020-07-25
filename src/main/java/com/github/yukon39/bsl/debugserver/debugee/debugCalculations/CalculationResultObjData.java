package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

@Data
public class CalculationResultObjData {
    private ViewInterface viewInterface;
    private CalculationResultContextPropertyInfo[] valueOfContextPropInfo;
    private CalculationResultEnumAndColPropertyInfo[] valueOfCollectionInfo;
    private CalculationResultEnumAndColPropertyInfo[] valueOfEnumInfo;
}
