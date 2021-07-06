package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Тип модуля
 */
public enum BSLModuleType {

    /**
     * Модуль конфигурации
     */
    @XmlEnumValue("ConfigModule")
    CONFIG_MODULE("ConfigModule"),

    /**
     * Модуль системной формы
     */
    @XmlEnumValue("SystemFormModule")
    SYSTEM_FORM_MODULE("SystemFormModule"),

    /**
     * Системный модуль
     */
    @XmlEnumValue("SystemModule")
    SYSTEM_MODULE("SystemModule"),

    /**
     * Модуль внешней обработки/отчета
     */
    @XmlEnumValue("ExtMDModule")
    EXT_MD_MODULE("ExtMDModule"),

    /**
     * Модуль расширения
     */
    @XmlEnumValue("ExtensionModule")
    EXTENSION_MODULE("ExtensionModule");

    private final String literal;

    BSLModuleType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}