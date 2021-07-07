package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Описание информации о новом значении локальной переменной или свойства контекста
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class NewValueInfo {

    /**
     * Способ указания нового значения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private NewValueVariant variant;

    /**
     * Новое значение, указанное в виде готового значения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Object value;

    /**
     * Новое значение, указанное в виде выражения для вычисления
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String valueExpression;
}
