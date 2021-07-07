package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос о получении данных для активизации предмета отладки
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetForegroundHelperDataResponse implements IRDBGResponse {

    /**
     * Данные для активации окна
     */
    private ForegroundWindowData data;
}
