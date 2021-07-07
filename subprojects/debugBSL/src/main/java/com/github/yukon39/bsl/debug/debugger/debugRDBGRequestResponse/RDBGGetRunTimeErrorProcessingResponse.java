package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на получение способа обработки рантайм ошибок
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetRunTimeErrorProcessingResponse implements IRDBGResponse {

    /**
     * Способ обработки рантайм ошибок
     */
    private RteFilterStorage state;
}
