package com.github.yukon39.bsl.debugserver.debugee.debugArea;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetType;
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
