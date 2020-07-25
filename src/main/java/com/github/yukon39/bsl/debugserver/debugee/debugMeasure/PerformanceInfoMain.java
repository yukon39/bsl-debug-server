package com.github.yukon39.bsl.debugserver.debugee.debugMeasure;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
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
