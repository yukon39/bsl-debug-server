package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Ответ на запрос о получении списка модулей, недоступных для отладки
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetInaccessibleModulesResponse implements IRDBGResponse {

    /**
     * Состав идентификаторов модулей, недоступных для отладки
     */
    private final List<BSLModuleIdInternal> moduleID = new ArrayList<>();
}
