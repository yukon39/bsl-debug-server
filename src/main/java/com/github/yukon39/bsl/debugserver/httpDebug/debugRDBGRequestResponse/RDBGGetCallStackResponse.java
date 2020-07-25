package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import lombok.Data;

import java.util.List;

@Data
public class RDBGGetCallStackResponse implements IRDBGResponse {
    private List<StackItemViewInfoData> callStack;
}
