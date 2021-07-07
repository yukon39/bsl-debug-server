package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос об отключении клиентской части отладчика
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGDetachDebugUIResponse implements IRDBGResponse {

    /**
     * Результат выполнения отключения
     */
    private Boolean result;
}
