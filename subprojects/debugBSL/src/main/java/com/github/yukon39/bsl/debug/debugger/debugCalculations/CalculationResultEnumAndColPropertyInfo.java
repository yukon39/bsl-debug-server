package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *  Описание элемента коллекции
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class CalculationResultEnumAndColPropertyInfo {

    /**
     * Описание собственно значения элемента коллекции
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private BaseValueInfoData valueInfo;

    /**
     * Информация о контексте
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<CalculationResultContextPropertyInfo> valueOfContextPropInfo = new ArrayList<>();
}
