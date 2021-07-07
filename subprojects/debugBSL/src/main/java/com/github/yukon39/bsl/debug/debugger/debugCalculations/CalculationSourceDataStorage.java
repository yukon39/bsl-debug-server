package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Привязка вычисляемого выражения к стеку
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class CalculationSourceDataStorage {

    /**
     * Уровень стека вызовов, на котором вычисляется выражение
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer stackLevel;

    /**
     * Выражение для вычисления
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private SourceCalculationDataInfo srcCalcInfo;

    /**
     * Опции формирования представления текстового значения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private DbgPresentationOptionsOfStringValue presOptions;
}
