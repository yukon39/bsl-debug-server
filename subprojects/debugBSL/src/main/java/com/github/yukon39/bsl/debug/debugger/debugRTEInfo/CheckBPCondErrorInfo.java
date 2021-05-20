package com.github.yukon39.bsl.debug.debugger.debugRTEInfo;

import com.github.yukon39.bsl.debug.data.core.GenericException;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

@Data
public class CheckBPCondErrorInfo {
    private String bpCondition;
    private GenericException exception;
    private BSLModuleIdInternal moduleID;
    private Integer lineNo;
}
