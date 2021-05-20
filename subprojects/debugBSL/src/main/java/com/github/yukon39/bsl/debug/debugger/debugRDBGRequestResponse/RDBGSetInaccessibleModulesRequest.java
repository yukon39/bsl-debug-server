package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGSetInaccessibleModulesRequest extends RDbgBaseRequest implements IRDBGRequest {
    private final List<BSLModuleIdInternal> moduleID = new ArrayList<>();
}
