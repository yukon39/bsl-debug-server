package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum BSLModuleType {

    @XmlEnumValue("ConfigModule")
    CONFIG_MODULE("ConfigModule"),

    @XmlEnumValue("SystemFormModule")
    SYSTEM_FORM_MODULE("SystemFormModule"),

    @XmlEnumValue("SystemModule")
    SYSTEM_MODULE("SystemModule"),

    @XmlEnumValue("ExtMDModule")
    EXT_MD_MODULE("ExtMDModule"),

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