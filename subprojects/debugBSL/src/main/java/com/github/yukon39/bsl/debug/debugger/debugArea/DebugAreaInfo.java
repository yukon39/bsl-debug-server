package com.github.yukon39.bsl.debug.debugger.debugArea;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetType;
import lombok.Data;

@Data
public class DebugAreaInfo {
    String name;
    Boolean useMaskOfDSArea;
    DebugAreaDSPairInfo[] dsPairInfo;
    Boolean useMaskOfTargetTypes;
    DebugTargetType[] targetType;
    Boolean useMaskOfUsers;
    DebugAreaUserInfo[] userInfo;
}
