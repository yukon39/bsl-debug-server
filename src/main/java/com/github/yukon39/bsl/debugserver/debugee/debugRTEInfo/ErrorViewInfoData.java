package com.github.yukon39.bsl.debugserver.debugee.debugRTEInfo;

import com.github.yukon39.bsl.debugserver.debugee.core.GenericException;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import lombok.Data;

@Data
public class ErrorViewInfoData {
    private ErrorType errorType;
    private GenericException exception;
    private BSLModuleIdInternal moduleID;
    private String moduleName;
    private Integer lineNo;
    private char[] descr;
    private String srcLine;
    private Integer posInLine;
    private char[] presentation;
    private char[] longPresentation;
    private StackItemViewInfoData[] callStack;
}
