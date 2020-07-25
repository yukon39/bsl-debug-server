package com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

@Data
public class ModuleBPInfoInternal {
    BSLModuleIdInternal id;
    BreakpointInfo[] bpInfo;
}
