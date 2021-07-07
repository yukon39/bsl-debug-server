package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос о проверке возможности принудительного завершения работы предмета отладки
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGCheckTerminateAbilityRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Идентификатор предмета отладки
     */
    private DebugTargetId id;
}
