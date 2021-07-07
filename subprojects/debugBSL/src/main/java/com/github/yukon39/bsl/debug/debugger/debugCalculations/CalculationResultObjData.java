package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание собственно результата вычисления выражения
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class CalculationResultObjData {

    /**
     * Интерфейс, применительно к которому описывается информация о значении
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private ViewInterface viewInterface;

    /**
     * Информация о контексте
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<CalculationResultContextPropertyInfo> valueOfContextPropInfo = new ArrayList<>();

    /**
     * Информация об элементе коллекции
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<CalculationResultEnumAndColPropertyInfo> valueOfCollectionInfo = new ArrayList<>();

    /**
     * Информация об элементе индексируемой коллекции
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<CalculationResultEnumAndColPropertyInfo> valueOfEnumInfo = new ArrayList<>();
}
