package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugStepAction;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос на выполнение шага или останова
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGStepRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Идентификатор предмета отладки
     */
    @XmlElement(required = true)
    private DebugTargetIdLight targetID;

    /**
     * Тип шага или останова
     */
    @XmlElement(required = true)
    private DebugStepAction action;

    /**
     * Признак того, что команду пошаговой отладки (внутрь, наружу, через)
     * нужно делать только в указанном предмете отладки, не продолжая работу
     * других остановленных предметов отладки
     */
    @XmlElement
    private Boolean simple;

    /**
     * Идентификатор предмета отладки, который вызвал срабатывание
     */
    @XmlElement
    private DebugTargetIdLight triggeredTargetID;
}
