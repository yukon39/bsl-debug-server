package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DbgTargetStateInfo;
import lombok.Data;

import java.util.List;

@Data
public class RDBGStepResponse implements IRDBGResponse {
    private List<DbgTargetStateInfo> item;
}
