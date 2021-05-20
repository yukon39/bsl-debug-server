package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetInaccessibleModulesResponse implements IRDBGResponse {
    private final List<BSLModuleIdInternal> moduleID = new ArrayList<>();
}
