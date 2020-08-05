package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum ViewInterface {

    @XmlEnumValue("none")
    NONE("none"),

    @XmlEnumValue("context")
    CONTEXT("context"),

    @XmlEnumValue("enum")
    ENUM("enum"),

    @XmlEnumValue("collection")
    COLLECTION("collection");

    private final String literal;

    ViewInterface(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}