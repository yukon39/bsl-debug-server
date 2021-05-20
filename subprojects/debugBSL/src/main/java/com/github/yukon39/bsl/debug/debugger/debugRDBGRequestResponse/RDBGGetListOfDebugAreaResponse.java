package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugArea.DebugAreaInfo;
import lombok.Data;

@Data
public class RDBGGetListOfDebugAreaResponse implements IRDBGResponse {
    private DebugAreaInfo[] debugAreaInfo;
}
