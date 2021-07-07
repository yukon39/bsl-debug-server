package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Варианты вычисления выражения
 */
public enum ViewInterface {

    /**
     * Детализировать результаты вычислений не нужно
     */
    @XmlEnumValue("none")
    NONE("none"),

    /**
     * Рассматривать результат вычисления как контекст
     */
    @XmlEnumValue("context")
    CONTEXT("context"),

    /**
     * Рассматривать результат вычисления как перечисление
     */
    @XmlEnumValue("enum")
    ENUM("enum"),

    /**
     * Рассматривать результат вычисления как индексируемую коллекцию
     */
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