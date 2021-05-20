package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RDBGSGetDbgTargetsResponse implements IRDBGResponse {
    private final List<DebugTargetId> id = new ArrayList<>();
}
