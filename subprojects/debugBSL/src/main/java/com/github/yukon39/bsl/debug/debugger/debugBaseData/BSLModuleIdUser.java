package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Внешний идентификатор модуля
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class BSLModuleIdUser {

    /**
     * Тип модуля
     */
    @XmlElement
    private BSLModuleType type;

    /**
     * URL местоположения внешних метаданных
     */
    @XmlElement(name = "URL")
    private String url = "";

    /**
     * Имя расширения конфигурации
     */
    @XmlElement(name = "extensionName")
    private String extensionName = "";

    /**
     * Идентификатор объекта метаданных, к которому относится модуль
     */
    @XmlElement(name = "MDObject")
    private String mdObject;

    /**
     * Идентификатор свойства объекта метаданных, в котором хранится модуль
     */
    @XmlElement(name = "MDProperty")
    private String mdProperty;

    /**
     * Расширенный идентификатор модуля
     */
    @XmlElement(name = "ExtId")
    private Integer extId = 0;

    /**
     * Версия объекта метаданных модуля
     */
    @XmlElement(name = "Version")
    private String version;
}
