package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum DbgTargetState {

    @XmlEnumValue("Undefined")
    UNDEFINED("Undefined"),

    @XmlEnumValue("NotRegistered")
    NOT_REGISTERED("NotRegistered"),

    @XmlEnumValue("Registered")
    REGISTERED("Registered"),

    @XmlEnumValue("WaitDebugger")
    WAIT_DEBUGGER("WaitDebugger"),

    @XmlEnumValue("Worked")
    WORKED("Worked"),

    @XmlEnumValue("StopOnNextLine")
    STOP_ON_NEXT_LINE("StopOnNextLine"),

    @XmlEnumValue("Evaluating")
    EVALUATING("Evaluating"),

    @XmlEnumValue("Terminating")
    TERMINATING("Terminating"),

    @XmlEnumValue("Last")
    LAST("Last");

    private final String literal;

    DbgTargetState(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}