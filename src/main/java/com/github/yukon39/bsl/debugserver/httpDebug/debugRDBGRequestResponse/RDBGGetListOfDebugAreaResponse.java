package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugArea.DebugAreaInfo;
import lombok.Data;

@Data
public class RDBGGetListOfDebugAreaResponse implements IRDBGResponse {
    private DebugAreaInfo[] debugAreaInfo;
}
