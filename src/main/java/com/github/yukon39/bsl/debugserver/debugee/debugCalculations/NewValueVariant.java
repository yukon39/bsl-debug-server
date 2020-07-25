package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

public enum NewValueVariant {

    unknown ("unknown"),
    val ("val"),
    expr ("expr");

    private final String literal;

    NewValueVariant(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
