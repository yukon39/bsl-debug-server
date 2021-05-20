package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;

public enum AttachDebugUIResult {

    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),

    @XmlEnumValue("registered")
    REGISTERED("registered"),

    @XmlEnumValue("credentialsRequired")
    CREDENTIALS_REQUIRED("credentialsRequired"),

    @XmlEnumValue("ibInDebug")
    IB_IN_DEBUG("ibInDebug"),

    @XmlEnumValue("notRegistered")
    NOT_REGISTERED("notRegistered");

    private final String literal;

    AttachDebugUIResult(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}
