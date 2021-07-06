package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Описание шага отладки
 */
public enum DebugStepAction {

    /**
     * Неизвестное состояние
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * Выполнить шаг
     */
    @XmlEnumValue("Step")
    STEP("Step"),

    /**
     * Выполнить шаг внутрь
     */
    @XmlEnumValue("StepIn")
    STEP_IN("StepIn"),

    /**
     * Выполнить шаг наружу
     */
    @XmlEnumValue("StepOut")
    STEP_OUT("StepOut"),

    /**
     * Продолжить отладку
     */
    @XmlEnumValue("Continue")
    CONTINUE("Continue");

    private final String literal;

    DebugStepAction(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}