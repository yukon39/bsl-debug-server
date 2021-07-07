package com.github.yukon39.bsl.debug.debugger.debugCalculations;


import com.github.yukon39.bsl.debug.utils.StringUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.Date;

/**
 * Описание полученного значения
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class BaseValueInfoData {

    /**
     * Идентификатор типа свойства
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer typeCode = 0; // UNDEFINED

    /**
     * Текстовое представление названия типа
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String typeName = "";

    /**
     * Истина, если тип значения Число
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer valueDecimal;

    /**
     * Истина, если тип значения Строка
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String valueString;

    /**
     * Истина, если тип значения Дата/время
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Date valueDateTime;

    /**
     * Истина, если тип значения Булево
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean valueBoolean;

    /**
     * Текстовое представление значения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private byte[] pres;

    /**
     * Признак корректного получения текстового представления значения свойства
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean presProcessedCorrectly = Boolean.TRUE;

    /**
     * Признак того, что свойство при просмотре можно раскрывать (поддерживается контекст и число свойств в нем > 0)
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isExpandable;

    /**
     * Поддерживает ли значение интерфейс контекста
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isSupportIContext;

    /**
     * Поддерживает ли значение интерфейс коллекции
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isIIndexedCollectionRO;

    /**
     * Поддерживает ли значение интерфейс перечисления
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isSupportIEnumValue;

    public String getPresentation() {
        return StringUtils.toString(pres);
    }
}