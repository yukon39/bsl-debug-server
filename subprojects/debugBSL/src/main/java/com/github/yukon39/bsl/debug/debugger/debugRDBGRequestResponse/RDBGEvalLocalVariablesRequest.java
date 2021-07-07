package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Запрос для вычисления локальных переменных
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGEvalLocalVariablesRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Время для ожидания результата вычисления выражения (в милисекундах): 0 - без ожидания результата
     */
    @XmlElement
    private Integer calcWaitingTime;

    /**
     * Идентификатор предмета отладки
     */
    @XmlElement
    private DebugTargetIdLight targetID;

    /**
     * Выражения для вычисления:
     * Первое из выражений описывает вычисление собственно локальных переменных,
     * а последующие выражения (если есть) описывают некоторые другие выражения
     * для вычисления, которые полезно указывать, например, в том случае, если
     * нужно сразу вычислить выражения, соответствующие раскрытым узлам в отладчике
     */
    @XmlElement
    private final List<CalculationSourceDataStorage> expr = new ArrayList<>();
}
