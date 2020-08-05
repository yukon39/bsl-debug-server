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
        "expressionID",
        "expressionResultID",
        "calcItem",
        "interfaces"
})
public class SourceCalculationDataInfo {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<SourceCalculationDataItem> calcItem = new ArrayList<>();

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<ViewInterface> interfaces = new ArrayList<>();

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private UUID expressionID;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private UUID expressionResultID;
}
