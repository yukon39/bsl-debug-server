package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugArea.DebugAreaInfo;
import com.github.yukon39.bsl.debugserver.debugee.debugAutoAttach.DebugAutoAttachSettings;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HTTPServerInitialDebugSettingsData extends HTTPInitialDebugSettingsData {

    DebugAreaInfo[] debugAreaInfo;
    DebugAutoAttachSettings autoAttachSettings;
}
