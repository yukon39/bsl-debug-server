package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Завершение обновления ИБ в клиентской части отладчика - ответ
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGFinishUpdateIBResponse implements IRDBGResponse {

    /**
     * Результат выполнения отключения
     */
    private Boolean result;
}
