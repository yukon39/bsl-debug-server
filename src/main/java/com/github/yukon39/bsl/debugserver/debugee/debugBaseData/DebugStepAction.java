package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;
import org.jetbrains.annotations.NotNull;

public enum DebugStepAction {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    @XmlEnumValue("Step")
    STEP("Step"),

    @XmlEnumValue("StepIn")
    STEP_IN("StepIn"),

    @XmlEnumValue("StepOut")
    STEP_OUT("StepOut"),

    @XmlEnumValue("Continue")
    CONTINUE("Continue");

    private final String literal;

    DebugStepAction(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}