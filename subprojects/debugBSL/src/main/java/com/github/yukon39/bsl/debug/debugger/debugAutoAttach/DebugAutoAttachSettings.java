package com.github.yukon39.bsl.debug.debugger.debugAutoAttach;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание настроек автоматического подключения предметов отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugAutoAttach")
public class DebugAutoAttachSettings {

    /**
     * Тип предмета отладки для автоматического подключения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugAutoAttach")
    private final List<DebugTargetType> targetType = new ArrayList<>();

    /**
     * Имена областей отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugAutoAttach")
    private final List<String> areaName = new ArrayList<>();
}