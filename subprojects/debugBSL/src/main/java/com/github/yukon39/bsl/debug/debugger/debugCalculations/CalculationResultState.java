package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Описание состояния результата вычисления выражения
 */
public enum CalculationResultState {

    /**
     * Результат вычисления выражения не определен
     */
    @XmlEnumValue("undefined")
    UNDEFINED("undefined"),

    /**
     * Выражение вычислено корректно
     */
    @XmlEnumValue("correctly")
    CORRECTLY("correctly"),

    /**
     * Выражение вычислено с ошибками
     */
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
