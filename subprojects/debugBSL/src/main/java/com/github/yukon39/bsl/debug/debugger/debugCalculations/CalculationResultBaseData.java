package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Описание выражения для вычисления и результатов вычисления
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class CalculationResultBaseData {

    /**
     * Состояние вычисления выражение
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private CalculationResultState evalResultState;

    /**
     * Идентификатор результата вычисления выражения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private UUID expressionResultID;

    /**
     * Информация о значении, являющемся результатом вычисления выражения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private BaseValueInfoData resultValueInfo;

    /**
     * Состав поддерживаемых по результатам тестирования интерфейсов
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<ViewInterface> testedAndSupportedInterface = new ArrayList<>();

    /**
     * Собственно результат вычисления выражения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private CalculationResultObjData calculationResult;

    /**
     * Признак того, что при вычислении выражения возникли ошибки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean errorOccurred;

    /**
     * Если случилась ошибка - текст ошибки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private byte[] exceptionStr;

    /**
     * Имя локальной переменной (сюда на стороне рантайма будут записываться имена локальных переменных)
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String localVariableName;
}
