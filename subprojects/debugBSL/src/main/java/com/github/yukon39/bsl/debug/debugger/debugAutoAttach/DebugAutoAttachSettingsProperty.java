package com.github.yukon39.bsl.debug.debugger.debugAutoAttach;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

/**
 * Список настроек автоматического подключения предметов отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugAutoAttach")
public class DebugAutoAttachSettingsProperty {

    /**
     * Настройки автоматического подключения предметов отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugAutoAttach")
    private DebugAutoAttachSettings autoAttachSettings;
}
