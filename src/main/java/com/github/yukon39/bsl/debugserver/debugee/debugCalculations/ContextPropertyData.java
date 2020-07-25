package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

@Data
public class ContextPropertyData {
    private String propName;
    private Boolean isReadable;
    private Boolean isWritable;
    private Boolean isReaded;
    private char[] errorStr;
}
