package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugArea.DebugAreaInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGSetListOfDebugAreaRequest extends RDbgBaseRequest {
    private DebugAreaInfo[] debugAreaInfo;
}
