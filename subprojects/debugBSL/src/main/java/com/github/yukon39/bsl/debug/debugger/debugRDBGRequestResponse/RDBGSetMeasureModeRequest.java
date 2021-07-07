package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Запрос на установку состояния режима замера производительности
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGSetMeasureModeRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Идентификатор сессии замера производительности
     */
    private UUID measureModeSeanceID;
}
