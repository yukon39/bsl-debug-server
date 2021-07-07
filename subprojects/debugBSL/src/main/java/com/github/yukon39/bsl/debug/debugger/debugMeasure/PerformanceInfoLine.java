package com.github.yukon39.bsl.debug.debugger.debugMeasure;

import lombok.Data;

/**
 * Информация о времени исполнения одной строки кода
 */
@Data
public class PerformanceInfoLine {

    /**
     * Номер строки
     */
    private Integer lineNo;

    /**
     * Число вызовов
     */
    private Integer frequency;

    /**
     * Продолжительность исполнения
     */
    private Double durability;

    /**
     * Чистая продолжительность исполнения
     */
    private Double pureDurability;

    /**
     * Признак вызова серверной функции
     */
    private Byte serverCallSignal;
}
