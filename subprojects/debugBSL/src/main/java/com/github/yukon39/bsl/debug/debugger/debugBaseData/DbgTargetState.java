package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Состояние предмета отладки
 */
public enum DbgTargetState {

    /**
     * Состояние не определено
     */
    @XmlEnumValue("Undefined")
    UNDEFINED("Undefined"),

    /**
     * Предмет отладки не зарегистрирован
     */
    @XmlEnumValue("NotRegistered")
    NOT_REGISTERED("NotRegistered"),

    /**
     * Предмет отладки зарегистрирован
     */
    @XmlEnumValue("Registered")
    REGISTERED("Registered"),

    /**
     * Предмет отладки ожидает подключения отладчика
     */
    @XmlEnumValue("WaitDebugger")
    WAIT_DEBUGGER("WaitDebugger"),

    /**
     * Предмет отладки работает
     */
    @XmlEnumValue("Worked")
    WORKED("Worked"),

    /**
     * Предмет отладки остановлен на строке кода
     */
    @XmlEnumValue("StopOnNextLine")
    STOP_ON_NEXT_LINE("StopOnNextLine"),

    /**
     * Предмет отладки занимается вычислением выражений или изменением значений переменных
     */
    @XmlEnumValue("Evaluating")
    EVALUATING("Evaluating"),

    /**
     * Предмет отладки завершает своб работу по просьбе отладчика
     */
    @XmlEnumValue("Terminating")
    TERMINATING("Terminating"),

    @XmlEnumValue("Last")
    LAST("Last");

    private final String literal;

    DbgTargetState(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}