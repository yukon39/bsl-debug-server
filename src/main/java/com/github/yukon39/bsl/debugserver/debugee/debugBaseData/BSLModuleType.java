package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;
import org.jetbrains.annotations.NotNull;

public enum BSLModuleType {

    @XmlEnumValue("ConfigModule")
    CONFIG_MODULE("ConfigModule"),

    @XmlEnumValue("ConfigModule")
    SYSTEM_FORM_MODULE("SystemFormModule"),

    @XmlEnumValue("ConfigModule")
    SYSTEM_MODULE("SystemModule"),

    @XmlEnumValue("ConfigModule")
    EXT_MD_MODULE("ExtMDModule"),

    @XmlEnumValue("ConfigModule")
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