package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Описание типа элемента вычисляемого выражения
 */
public enum SourceCalculationDataItemType {

    /**
     * Выражение для вычисления
     */
    @XmlEnumValue("expression")
    EXPRESSION("expression"),

    /**
     * Имя свойства для вычисления
     */
    @XmlEnumValue("property")
    PROPERTY("property"),

    /**
     * Индекс в коллекции для вычисления
     */
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
