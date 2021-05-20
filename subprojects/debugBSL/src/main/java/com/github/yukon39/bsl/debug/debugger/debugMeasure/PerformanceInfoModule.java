package com.github.yukon39.bsl.debug.debugger.debugMeasure;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

@Data
public class PerformanceInfoModule {
    private BSLModuleIdInternal moduleID;
    private PerformanceInfoLine[] lineInfo;
}
