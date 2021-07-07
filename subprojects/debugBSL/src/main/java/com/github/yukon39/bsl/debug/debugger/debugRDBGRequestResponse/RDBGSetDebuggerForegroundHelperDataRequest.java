package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Запрос для передачи данных для активизации окна отладчика предмету отладки
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGSetDebuggerForegroundHelperDataRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Идентификатор получателя
     */
    private UUID receiverID;

    /**
     * Данные для активации окна
     */
    private ForegroundWindowData data;
}
