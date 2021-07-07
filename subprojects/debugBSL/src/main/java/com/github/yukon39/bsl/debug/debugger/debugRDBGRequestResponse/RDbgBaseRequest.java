package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.UUID;

/**
 * Базовый тип для всех запросов сервера отладки со стороны клиентской части отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RDbgBaseRequest {

    /**
     * Имя информационной базы
     */
    @XmlElement(required = true)
    private String infoBaseAlias;

    /**
     * Идентификатор клиентской части отладки
     */
    @XmlElement(required = true)
    private UUID idOfDebuggerUI;
}
