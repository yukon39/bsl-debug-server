package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum NewValueVariant {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),

    @XmlEnumValue("val")
    VAL("val"),

    @XmlEnumValue("expr")
    EXPR("expr");

    private final String literal;

    NewValueVariant(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
