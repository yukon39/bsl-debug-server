package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Внутненний идентификатор модуля
 */
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class BSLModuleIdInternal {

    /**
     * Тип модуля
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private BSLModuleType type = BSLModuleType.CONFIG_MODULE;

    /**
     * URL местоположения внешних метаданных
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData", name = "URL")
    private String url = "";

    /**
     * Имя расширения конфигурации
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String extensionName = "";

    /**
     * Идентификатор объекта метаданных, к которому относится модуль
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID objectID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * Идентификатор свойства объекта метаданных, в котором хранится модуль
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID propertyID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    /**
     * Расширенный идентификатор модуля
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer extId = 0;

    /**
     * Версия объекта метаданных модуля
     */
    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String version;
}
