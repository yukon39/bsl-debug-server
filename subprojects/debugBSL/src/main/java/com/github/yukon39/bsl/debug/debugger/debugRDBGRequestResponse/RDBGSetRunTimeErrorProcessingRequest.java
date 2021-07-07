package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос на установку способа обработки рантайм ошибок
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGSetRunTimeErrorProcessingRequest extends RDbgBaseRequest implements IRDBGRequest{

    /**
     * Способ обработки рантайм ошибок
     */
    private RteFilterStorage state;
}
