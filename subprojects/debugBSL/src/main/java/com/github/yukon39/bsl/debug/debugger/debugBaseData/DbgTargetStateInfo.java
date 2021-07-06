package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Идентификатор предмета отладки и описание его состояния
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DbgTargetStateInfo {

    /**
     * Идентификатор предмета отладки в виде строки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DebugTargetIdStr targetIDStr;

    /**
     * Идентификатор предмета отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DebugTargetId targetID;

    /**
     * Состояние работы предмета отладки в виде числа
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer stateNum = 0;

    /**
     * Состояние работы предмета отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DbgTargetState state;
}
