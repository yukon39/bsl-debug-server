package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdUser;
import lombok.Data;

@Data
public class ModuleBPInfoUser {
    BSLModuleIdUser id;
    BreakpointInfo[] bpInfo;
}
