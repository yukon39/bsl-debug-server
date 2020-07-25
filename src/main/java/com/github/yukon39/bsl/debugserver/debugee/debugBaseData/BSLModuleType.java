package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

public enum BSLModuleType {

    ConfigModule("ConfigModule"),
    SystemFormModule("SystemFormModule"),
    SystemModule("SystemModule"),
    ExtMDModule("ExtMDModule"),
    ExtensionModule("ExtensionModule");

    private final String literal;

    BSLModuleType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }
}