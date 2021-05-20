package com.github.yukon39.bsl.debug.debugger.debugMeasure;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import lombok.Data;

import java.util.UUID;

@Data
public class PerformanceInfoMain {
    private DebugTargetId targetID;
    private Double totalDurability;
    private Double totalIndepServerWorkTime;
    private Integer performanceFrequency;
    private PerformanceInfoModule[] moduleData;
    private UUID sessionID;
}
