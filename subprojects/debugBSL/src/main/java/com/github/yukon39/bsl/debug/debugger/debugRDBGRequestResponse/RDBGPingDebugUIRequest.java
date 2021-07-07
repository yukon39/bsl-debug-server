package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос о наличии событий для клиентской части отладчика
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGPingDebugUIRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Записывать данные по возможности как строки
     */
    private Boolean saveDataAsString = Boolean.FALSE;
}
