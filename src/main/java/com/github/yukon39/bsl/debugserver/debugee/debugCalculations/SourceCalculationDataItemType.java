package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum SourceCalculationDataItemType {

    @XmlEnumValue("expression")
    EXPRESSION("expression"),

    @XmlEnumValue("property")
    PROPERTY("property"),

    @XmlEnumValue("index")
    INDEX("index");

    private final String literal;

    SourceCalculationDataItemType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
