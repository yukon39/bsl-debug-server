package com.github.yukon39.bsl.debugserver.debugee.debugRTEInfo;

public enum ErrorType {

    Unknown("Unknown"),
    BSLCompile("BSLCompile"),
    BSLRuntime("BSLRuntime"),
    BreakpointBSLCompile("BreakpointBSLCompile"),
    BreakpointBSLRuntime("BreakpointBSLRuntime");

    private final String literal;

    ErrorType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
