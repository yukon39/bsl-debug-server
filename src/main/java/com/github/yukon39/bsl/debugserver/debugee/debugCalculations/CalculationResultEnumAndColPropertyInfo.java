package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        "valueInfo",
        "valueOfContextPropInfo"
})
public class CalculationResultEnumAndColPropertyInfo {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<CalculationResultContextPropertyInfo> valueOfContextPropInfo = new ArrayList<>();

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private BaseValueInfoData valueInfo;
}
