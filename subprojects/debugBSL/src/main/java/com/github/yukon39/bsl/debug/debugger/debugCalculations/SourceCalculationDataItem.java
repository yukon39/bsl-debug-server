package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Описание элемента из вычисляемого выражения
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class SourceCalculationDataItem {

    /**
     * Тип элемента
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private SourceCalculationDataItemType itemType;

    /**
     * Выражение
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String expression = "";

    /**
     * Имя свойства
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String property = "";

    /**
     * Индекс элемента коллекции
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer index;
}
