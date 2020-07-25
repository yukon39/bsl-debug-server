package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum DbgTargetState {

    @XmlEnumValue("Undefined")
    Undefined("Undefined"),

    @XmlEnumValue("NotRegistered")
    NotRegistered("NotRegistered"),

    @XmlEnumValue("Registered")
    Registered("Registered"),

    @XmlEnumValue("WaitDebugger")
    WaitDebugger("WaitDebugger"),

    @XmlEnumValue("Worked")
    Worked("Worked"),

    @XmlEnumValue("StopOnNextLine")
    StopOnNextLine("StopOnNextLine"),

    @XmlEnumValue("Evaluating")
    Evaluating("Evaluating"),

    @XmlEnumValue("Terminating")
    Terminating("Terminating"),

    @XmlEnumValue("Last")
    Last("Last");

    private final String literal;

    DbgTargetState(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}