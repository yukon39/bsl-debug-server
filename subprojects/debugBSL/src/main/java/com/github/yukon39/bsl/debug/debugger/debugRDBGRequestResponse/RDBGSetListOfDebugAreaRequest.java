package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugArea.DebugAreaInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetListOfDebugAreaRequest extends RDbgBaseRequest {
    private DebugAreaInfo[] debugAreaInfo;
}
