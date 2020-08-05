package com.github.yukon39.bsl.debugserver.debugee.debugRTEInfo;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum ErrorType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    @XmlEnumValue("BSLCompile")
    BSL_COMPILE("BSLCompile"),

    @XmlEnumValue("BSLRuntime")
    BSL_RUNTIME("BSLRuntime"),

    @XmlEnumValue("BreakpointBSLCompile")
    BREAKPOINT_BSL_COMPILE("BreakpointBSLCompile"),

    @XmlEnumValue("BreakpointBSLRuntime")
    BREAKPOINT_BSL_RUNTIME("BreakpointBSLRuntime");

    private final String literal;

    ErrorType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
