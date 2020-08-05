package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        "evalResultState",
        "expressionResultID",
        "resultValueInfo",
        "testedAndSupportedInterface",
        "calculationResult",
        "errorOccurred",
        "exceptionStr",
        "localVariableName"
})
public class CalculationResultBaseData {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<ViewInterface> testedAndSupportedInterface = new ArrayList<>();

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private CalculationResultState evalResultState;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private UUID expressionResultID;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private BaseValueInfoData resultValueInfo;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private CalculationResultObjData calculationResult;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean errorOccurred;
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private byte[] exceptionStr;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String localVariableName;
}
