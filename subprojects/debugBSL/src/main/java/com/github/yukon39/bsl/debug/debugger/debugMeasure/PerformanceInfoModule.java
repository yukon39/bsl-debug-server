package com.github.yukon39.bsl.debug.debugger.debugMeasure;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Информация о времени исполнения строк модуля
 */
@Data
public class PerformanceInfoModule {

    /**
     * Идентификатор модуля
     */
    private BSLModuleIdInternal moduleID;

    /**
     * Информация о времени исполнения строк модуля
     */
    private final List<PerformanceInfoLine> lineInfo = new ArrayList<>();
}
