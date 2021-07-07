package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос для настройки автоматического подключения предметов отладки
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetAutoAttachSettingsResponse implements IRDBGResponse {
    /**
     * Настройки автоматического подключения предметов отладки
     */
    private DebugAutoAttachSettings autoAttachSettings;
}
