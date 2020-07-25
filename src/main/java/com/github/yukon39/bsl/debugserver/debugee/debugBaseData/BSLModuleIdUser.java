package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import lombok.Data;

@Data
public class BSLModuleIdUser {
    BSLModuleType type;
    String URL;
    String extensionName;
    String MDObject;
    String MDProperty;
    Integer ExtId;
    String Version;
}
