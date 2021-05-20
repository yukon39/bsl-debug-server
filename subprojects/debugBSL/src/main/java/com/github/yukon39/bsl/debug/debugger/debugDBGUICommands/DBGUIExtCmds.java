package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import jakarta.xml.bind.annotation.XmlEnumValue;
import org.jetbrains.annotations.NotNull;

public enum DBGUIExtCmds {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),

    @XmlEnumValue("targetStarted")
    TARGET_STARTED("targetStarted"),

    @XmlEnumValue("targetQuit")
    TARGET_QUIT("targetQuit"),

    @XmlEnumValue("correctedBP")
    CORRECTED_BP("correctedBP"),

    @XmlEnumValue("rteProcessing")
    RTE_PROCESSING("rteProcessing"),

    @XmlEnumValue("rteOnBPConditionProcessing")
    RTE_ON_BP_CONDITION_PROCESSING("rteOnBPConditionProcessing"),

    @XmlEnumValue("measureResultProcessing")
    MEASURE_RESULT_PROCESSING("measureResultProcessing"),

    @XmlEnumValue("callStackFormed")
    CALL_STACK_FORMED("callStackFormed"),

    @XmlEnumValue("exprEvaluated")
    EXPR_EVALUATED("exprEvaluated"),

    @XmlEnumValue("valueModified")
    VALUE_MODIFIED("valueModified"),

    @XmlEnumValue("errorViewInfo")
    ERROR_VIEW_INFO("errorViewInfo"),

    @XmlEnumValue("ForegroundHelperSet")
    FOREGROUND_HELPER_SET("ForegroundHelperSet"),

    @XmlEnumValue("ForegroundHelperRequest")
    FOREGROUND_HELPER_REQUEST("ForegroundHelperRequest"),

    @XmlEnumValue("ForegroundHelperProcess")
    FOREGROUND_HELPER_PROCESS("ForegroundHelperProcess");

    private final String literal;

    DBGUIExtCmds(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}