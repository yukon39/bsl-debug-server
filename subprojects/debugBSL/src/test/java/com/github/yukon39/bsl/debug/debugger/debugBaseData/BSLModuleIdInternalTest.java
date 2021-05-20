package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import com.github.yukon39.bsl.debug.data.ModulePropertyId;

import java.util.UUID;

public class BSLModuleIdInternalTest {

    public static BSLModuleIdInternal createTestObjectCommonModule() {

        var moduleId = new BSLModuleIdInternal();
        moduleId.setType(BSLModuleType.CONFIG_MODULE);
        moduleId.setObjectID(UUID.fromString("c38631d8-25e7-4405-b9e2-1132fe9eb470"));
        moduleId.setPropertyID(ModulePropertyId.COMMON_MODULE.getId());
        moduleId.setExtId(1);
        moduleId.setVersion("e0cd790848ab0c459cd3458e5132d90b00000000");

        return moduleId;
    }

    public static BSLModuleIdInternal createTestObjectCatalogManagerModule() {

        var moduleId = new BSLModuleIdInternal();
        moduleId.setType(BSLModuleType.CONFIG_MODULE);
        moduleId.setObjectID(UUID.fromString("3bf89955-e598-45e7-aabf-9a7f63a888b1"));
        moduleId.setPropertyID(ModulePropertyId.MANAGER_MODULE.getId());

        return moduleId;
    }
}