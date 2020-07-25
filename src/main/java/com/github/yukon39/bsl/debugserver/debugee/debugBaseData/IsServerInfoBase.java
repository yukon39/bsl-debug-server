package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum IsServerInfoBase {

    @XmlEnumValue("undefined")
    UNDEFINED("undefined"),

    @XmlEnumValue("true")
    TRUE("true"),

    @XmlEnumValue("false")
    FALSE("false");

    private final String literal;

    IsServerInfoBase(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}