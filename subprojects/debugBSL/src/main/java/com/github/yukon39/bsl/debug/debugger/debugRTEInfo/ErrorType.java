package com.github.yukon39.bsl.debug.debugger.debugRTEInfo;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Типы ошибок рантайма
 */
public enum ErrorType {

    /**
     * Неизвестнвый тип ошибки
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * Ошибка компиляции
     */
    @XmlEnumValue("BSLCompile")
    BSL_COMPILE("BSLCompile"),

    /**
     * Ошибка времени исполнения
     */
    @XmlEnumValue("BSLRuntime")
    BSL_RUNTIME("BSLRuntime"),

    /**
     * Ошибка компиляции условия точки останова
     */
    @XmlEnumValue("BreakpointBSLCompile")
    BREAKPOINT_BSL_COMPILE("BreakpointBSLCompile"),

    /**
     * Ошибка времени исполнения при вычислении условия точки останова
     */
    @XmlEnumValue("BreakpointBSLRuntime")
    BREAKPOINT_BSL_RUNTIME("BreakpointBSLRuntime");

    private final String literal;

    ErrorType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
