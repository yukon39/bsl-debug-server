package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * Описание состояний результата регистрации отладчика
 */
public enum AttachDebugUIResult {

    /**
     * Состояние не инициализировано
     */
    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),

    /**
     * Отладчик зарегистрирован
     */
    @XmlEnumValue("registered")
    REGISTERED("registered"),

    /**
     * Отказано в регистрации - отсутствует или неправильно указан пароль сервера отладки
     */
    @XmlEnumValue("credentialsRequired")
    CREDENTIALS_REQUIRED("credentialsRequired"),

    /**
     * Отказано в регистрации - информационная база уже отлаживается другим отладчиком
     */
    @XmlEnumValue("ibInDebug")
    IB_IN_DEBUG("ibInDebug"),

    /**
     * Отказано в регистрации - прочие ошибки
     */
    @XmlEnumValue("notRegistered")
    NOT_REGISTERED("notRegistered");

    private final String literal;

    AttachDebugUIResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
