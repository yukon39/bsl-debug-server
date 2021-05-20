package com.github.yukon39.bsl.debug.debugger.debugRTEInfo;

import com.github.yukon39.bsl.debug.data.core.GenericException;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
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
