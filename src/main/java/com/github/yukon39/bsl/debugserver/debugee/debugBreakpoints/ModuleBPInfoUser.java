package com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdUser;
import lombok.Data;

@Data
public class ModuleBPInfoUser {
    BSLModuleIdUser id;
    BreakpointInfo[] bpInfo;
}
