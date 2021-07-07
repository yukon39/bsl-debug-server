package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DbgTargetState;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос о состоянии предмета отладки
 */

@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetDbgTargetStateResponse implements IRDBGResponse {

    /**
     * Состояние предмета отладки
     */
    private DbgTargetState state;
}
