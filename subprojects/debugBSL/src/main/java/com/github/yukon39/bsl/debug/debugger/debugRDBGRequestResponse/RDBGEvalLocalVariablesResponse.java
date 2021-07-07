package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос о вычислении локальных переменных
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGEvalLocalVariablesResponse implements IRDBGResponse {

    /**
     * Результат вычисления выражения
     */
    private CalculationResultBaseData result;
}
