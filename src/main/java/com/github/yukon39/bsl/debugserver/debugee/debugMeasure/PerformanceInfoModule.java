package com.github.yukon39.bsl.debugserver.debugee.debugMeasure;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

@Data
public class PerformanceInfoModule {
    private BSLModuleIdInternal moduleID;
    private PerformanceInfoLine[] lineInfo;
}
