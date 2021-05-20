package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum CalculationResultState {

    @XmlEnumValue("undefined")
    UNDEFINED("undefined"),

    @XmlEnumValue("correctly")
    CORRECTLY("correctly"),

    @XmlEnumValue("withErrors")
    WITH_ERRORS("withErrors");

    private final String literal;

    CalculationResultState(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
