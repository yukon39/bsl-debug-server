package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

public enum ViewInterface {
    NONE("none"),
    CONTEXT("context"),
    ENUM("enum"),
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