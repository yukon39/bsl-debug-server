package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

import java.util.UUID;

@Data
public class CalculationResultBaseData {
    private CalculationResultState evalResultState;
    private UUID expressionResultID;
    private BaseValueInfoData resultValueInfo;
    private ViewInterface[] testedAndSupportedInterface;
    private CalculationResultObjData calculationResult;
    private Boolean errorOccurred;
    private char[] exceptionStr;
    private String localVariableName;
}
