package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class CalculationResultStorage {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private CalculationResultBaseData evalExprResBaseData;
}
