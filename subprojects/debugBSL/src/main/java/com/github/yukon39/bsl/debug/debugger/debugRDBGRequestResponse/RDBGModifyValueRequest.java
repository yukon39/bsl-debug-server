package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.NewValueInfo;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос на изменение значения локальной переменной или свойства контекста
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGModifyValueRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Идентификатор предмета отладки
     */
    private DebugTargetIdLight targetID;

    /**
     * Выражение для вычисления
     */
    private CalculationSourceDataStorage modifyDataPath;

    /**
     * Описание нового значения локальной переменной или свойства контекста
     */
    private NewValueInfo newValueInfo;

    /**
     * Время ожидания результата изменения значения переменной, мс
     * Значение по умолчанию: 60 секунд
     */
    private Integer timeout = 60000;
}
