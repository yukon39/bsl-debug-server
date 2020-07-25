package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import lombok.Data;

import java.util.UUID;

@Data
public class BSLModuleIdInternal {
    BSLModuleType type;
    String URL;
    String extensionName;
    UUID objectID;
    UUID propertyID;
    Integer extId;
    String version;
}
