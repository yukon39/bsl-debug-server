package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class SourceCalculationDataItem {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private SourceCalculationDataItemType itemType;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String expression;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String property;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer index;
}
