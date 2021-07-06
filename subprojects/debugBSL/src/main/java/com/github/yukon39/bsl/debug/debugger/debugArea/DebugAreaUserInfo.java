package com.github.yukon39.bsl.debug.debugger.debugArea;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Описание пользователя области отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DebugAreaUserInfo {

    /**
     * Имя пользователя
     */
    @XmlElement
    private String name;

    /**
     * Признак использования пользователя
     */
    @XmlElement
    private Boolean use;
}