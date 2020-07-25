package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugAutoAttach.DebugAutoAttachSettings;
import lombok.Data;

@Data
public class RDBGGetAutoAttachSettingsResponse implements IRDBGResponse {
    private DebugAutoAttachSettings autoAttachSettings;
}
