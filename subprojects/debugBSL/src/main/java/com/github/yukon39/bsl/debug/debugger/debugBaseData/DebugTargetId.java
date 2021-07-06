package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Идентификатор предмета отладки
 */
@EqualsAndHashCode(callSuper = true)
@Data()
@XmlAccessorType(XmlAccessType.NONE)
public class DebugTargetId extends DebugTargetIdLight {

    /**
     * Идентификатор сеанса
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID seanceId;

    /**
     * Номер сеанса
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer seanceNo;

    /**
     * Уникальный идентификатор экземпляра информационной базы, с которой работает предмет отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID infoBaseInstanceID;

    /**
     * Имя информационной базы
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String infoBaseAlias;

    /**
     * Указание того, является ли ИБ файловой или клиент/серверной
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private IsServerInfoBase isServerInfoBase = IsServerInfoBase.UNDEFINED;

    /**
     * Имя пользователя
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String userName;

    /**
     * Версия метаданных конфигурации
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String configVersion;

    /**
     * Тип предмета отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DebugTargetType targetType = DebugTargetType.UNKNOWN;
}
