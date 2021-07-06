package com.github.yukon39.bsl.debug.debugger.debugArea;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Описание информации о разделителе, входящем в область отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DebugAreaDSPairInfo {

    /**
     * Имя разделителя данных
     */
    @XmlElement
    private String name;

    /**
     * Значение разделителя данных
     */
    @XmlElement
    private Object value;

    /**
     * Признак использования значения разделителя данных
     */
    @XmlElement
    private Boolean use;
}