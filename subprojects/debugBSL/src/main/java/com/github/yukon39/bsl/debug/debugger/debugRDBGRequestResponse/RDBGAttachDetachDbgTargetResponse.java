package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос о подключении/отключении предмета отладки
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGAttachDetachDbgTargetResponse implements IRDBGResponse {

    /**
     * Результат выполнения операции
     */
    private Boolean result;
}
