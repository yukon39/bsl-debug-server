package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import lombok.Data;

@Data
public class RDBGGetAutoAttachSettingsResponse implements IRDBGResponse {
    private DebugAutoAttachSettings autoAttachSettings;
}
