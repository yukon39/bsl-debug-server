package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

/**
 * Точка останова
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
public class BreakpointInfo {

    /**
     * Номер строки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Integer line = 0;

    /**
     * Признак активности
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Boolean isActive = Boolean.TRUE;

    /**
     * Условие срабатывания
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    String condition = "";

    /**
     * Признак временной точки останова
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Boolean temp = Boolean.FALSE;

    /**
     * Признак пользовательскной точки останова
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Boolean user = Boolean.TRUE;
}
