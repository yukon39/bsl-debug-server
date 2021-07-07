package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Описание свойств контекста
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class ContextPropertyData {

    /**
     * Имя свойства
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String propName;

    /**
     * Признак доступности свойства для чтения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isReadable;

    /**
     * Признак доступности свойства для записи
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isWritable;

    /**
     * Признак успешного прочтения значения свойства
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isReaded;

    /**
     * Текст ошибки, возникшей при получении значения свойства
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private byte[] errorStr;
}
