package com.github.yukon39.bsl.debug.debugger.debugRTEFilter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Элемент отбора списка текста сообщений о рантайм ошибках
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RteFilterItem {

    /**
     * Признак необходимости использования элемента
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private Boolean include;

    /**
     * Подстрока из текста ошибки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    String str;
}
