package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.UUID;

/**
 * Ответ на запрос об идентификаторе клиентской части отладчика
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetDebugIDResponse implements IRDBGResponse {

    /**
     * Идентификатор клиентской части отладчика
     */
    private UUID idOfDebugUI;
}
