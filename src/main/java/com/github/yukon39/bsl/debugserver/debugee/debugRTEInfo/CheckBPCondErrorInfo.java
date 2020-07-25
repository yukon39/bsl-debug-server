package com.github.yukon39.bsl.debugserver.debugee.debugRTEInfo;

import com.github.yukon39.bsl.debugserver.debugee.core.GenericException;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

@Data
public class CheckBPCondErrorInfo {
    private String bpCondition;
    private GenericException exception;
    private BSLModuleIdInternal moduleID;
    private Integer lineNo;
}
