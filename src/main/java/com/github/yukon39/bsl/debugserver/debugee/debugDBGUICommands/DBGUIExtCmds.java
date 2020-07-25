package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

public enum DBGUIExtCmds {

    unknown ("unknown"),
    targetStarted ("targetStarted"),
    targetQuit ("targetQuit"),
    correctedBP ("correctedBP"),
    rteProcessing ("rteProcessing"),
    rteOnBPConditionProcessing ("rteOnBPConditionProcessing"),
    measureResultProcessing ("measureResultProcessing"),
    callStackFormed ("callStackFormed"),
    exprEvaluated ("exprEvaluated"),
    valueModified ("valueModified"),
    errorViewInfo ("errorViewInfo"),
    ForegroundHelperSet ("ForegroundHelperSet"),
    ForegroundHelperRequest ("ForegroundHelperRequest"),
    ForegroundHelperProcess ("ForegroundHelperProcess");

    private final String literal;

    DBGUIExtCmds(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}