package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

public enum SourceCalculationDataItemType {

    expression ("expression"),
    property ("property"),
    index ("index");

    private final String literal;

    SourceCalculationDataItemType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
