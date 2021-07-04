package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import java.util.UUID;

public class BSLModuleIdInternalTest {

    public static BSLModuleIdInternal createTestObjectCommonModule() {

        var moduleId = new BSLModuleIdInternal();
        moduleId.setType(BSLModuleType.CONFIG_MODULE);
        moduleId.setObjectID(UUID.fromString("c38631d8-25e7-4405-b9e2-1132fe9eb470"));
        moduleId.setPropertyID(UUID.fromString("d5963243-262e-4398-b4d7-fb16d06484f6")); // COMMON_MODULE
        moduleId.setExtId(1);
        moduleId.setVersion("e0cd790848ab0c459cd3458e5132d90b00000000");

        return moduleId;
    }

    public static BSLModuleIdInternal createTestObjectCatalogManagerModule() {

        var moduleId = new BSLModuleIdInternal();
        moduleId.setType(BSLModuleType.CONFIG_MODULE);
        moduleId.setObjectID(UUID.fromString("3bf89955-e598-45e7-aabf-9a7f63a888b1"));
        moduleId.setPropertyID(UUID.fromString("d1b64a2c-8078-4982-8190-8f81aefda192")); // MANAGER_MODULE

        return moduleId;
    }
}