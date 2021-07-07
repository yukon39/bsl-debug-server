package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос об изменении значения локальной переменной или свойства контекста
 */

@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGModifyValueResponse implements IRDBGResponse {

    /**
     * Состояние изменения значения переменной
     */
    private Boolean processed;

    /**
     * Результат вычисления выражения, полученного после изменения значения переменной
     */
    private CalculationResultBaseData newValueState;

    /**
     * Текст ошибки для случая, если значение локальной переменной изменить не удалось
     */
    private char[] errorDescr;
}
