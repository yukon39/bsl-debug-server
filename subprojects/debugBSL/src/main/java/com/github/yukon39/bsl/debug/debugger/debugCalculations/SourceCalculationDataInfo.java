package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Описание вычисляемого выражения
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class SourceCalculationDataInfo {

    /**
     * Идентификатор вычисляемого выражения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private UUID expressionID;

    /**
     * Идентификатор результата вычисления выражения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private UUID expressionResultID;

    /**
     * Выражения для вычисления
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<SourceCalculationDataItem> calcItem = new ArrayList<>();

    /**
     * Варианты вычисления выражения
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private final List<ViewInterface> interfaces = new ArrayList<>();




}
