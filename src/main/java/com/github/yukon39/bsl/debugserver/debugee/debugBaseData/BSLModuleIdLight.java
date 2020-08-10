package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
public final class BSLModuleIdLight {

    @NotNull
    private final UUID objectID;

    @NotNull
    private final UUID propertyID;

    @Contract("_ -> new")
    public static @NotNull BSLModuleIdLight of(BSLModuleIdInternal moduleId) {
        return new BSLModuleIdLight(moduleId.getObjectID(), moduleId.getPropertyID());
    }
}
