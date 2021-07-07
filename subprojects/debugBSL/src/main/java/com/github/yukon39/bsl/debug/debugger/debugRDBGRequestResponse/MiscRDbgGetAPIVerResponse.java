package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос о версии API сервера отладки
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class MiscRDbgGetAPIVerResponse implements IRDBGResponse {

    /**
     * Версия API сервера отладки, формата D.D.D где D - число
     */
    private String version;
}
