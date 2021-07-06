package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Определение типа информационной базы для предмета отладки
 */
public enum IsServerInfoBase {

    /**
     * Тип неопределен
     */
    @XmlEnumValue("undefined")
    UNDEFINED("undefined"),

    /**
     * Истина (информационная база клиент-серверная)
     */
    @XmlEnumValue("true")
    TRUE("true"),

    /**
     * Ложь (информационная база файловая)
     */
    @XmlEnumValue("false")
    FALSE("false");

    private final String literal;

    IsServerInfoBase(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}