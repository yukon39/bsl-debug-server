package com.github.yukon39.bsl.debugserver.debugee.debugMeasure;

import lombok.Data;

@Data
public class PerformanceInfoLine {
    private Integer lineNo;
    private Integer frequency;
    private Double durability;
    private Double pureDurability;
    private Byte serverCallSignal;
}
