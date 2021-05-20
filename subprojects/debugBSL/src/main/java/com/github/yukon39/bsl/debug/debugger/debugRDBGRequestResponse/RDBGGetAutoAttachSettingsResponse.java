package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetAutoAttachSettingsResponse implements IRDBGResponse {
    private DebugAutoAttachSettings autoAttachSettings;
}
