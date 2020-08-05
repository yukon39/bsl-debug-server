package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import com.github.yukon39.bsl.debugserver.context.ModulePropertyId;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
}