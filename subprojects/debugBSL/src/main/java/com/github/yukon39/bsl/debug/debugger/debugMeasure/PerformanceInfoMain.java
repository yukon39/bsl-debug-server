package com.github.yukon39.bsl.debug.debugger.debugMeasure;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Информация о замере производительности предмета отладки
 */
@Data
public class PerformanceInfoMain {

    /**
     * Идентификатор предмета отладки
     */
    private DebugTargetId targetID;

    /**
     * Общее время исполнения
     */
    private Double totalDurability;

    /**
     * Общее время работы кода на сервере (в секундах), исполняемого без вызова из клиентского кода
     */
    private Double totalIndepServerWorkTime;

    /**
     * Частота таймера высокого разрешения
     */
    private Integer performanceFrequency;

    /**
     * Информация об исполнении модулей
     */
    private final List<PerformanceInfoModule> moduleData = new ArrayList<>();

    /**
     * Идентификатор сессии замера производительности
     */
    private UUID sessionID;
}
