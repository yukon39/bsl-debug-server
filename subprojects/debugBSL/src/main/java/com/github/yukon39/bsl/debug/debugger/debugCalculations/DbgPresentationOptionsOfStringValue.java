package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Опции формирования представления текстового значения
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DbgPresentationOptionsOfStringValue {

    /**
     * Максимальная длина строки с результатом вычислений
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer maxTextSize;

    /**
     * Признак возврата только первой строки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean stopOnFirstEOL;

    public static DbgPresentationOptionsOfStringValue defaultOptions() {

        var options = new DbgPresentationOptionsOfStringValue();
        options.setMaxTextSize(100);
        options.setStopOnFirstEOL(true);

        return options;
    }
}
