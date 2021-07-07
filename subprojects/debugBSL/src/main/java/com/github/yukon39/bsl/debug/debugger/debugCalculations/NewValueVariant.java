package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Варианты указания нового значения
 */
public enum NewValueVariant {

    /**
     * Вариант указания нового значения не задан
     */
    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),

    /**
     * Новое значение задается в виде готового значения
     */
    @XmlEnumValue("val")
    VAL("val"),

    /**
     * Новое значение задается в виде выражения для вычисления
     */
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
