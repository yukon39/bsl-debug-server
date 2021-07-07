package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос на подключение клиентской части отладчика к информационной базе
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGAttachDebugUIRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Упакованный пароль сервера отладк
     */
    @XmlElement
    private byte[] credentials;

    /**
     * Опции отладчика
     */
    @XmlElement
    private DebuggerOptions options;
}
