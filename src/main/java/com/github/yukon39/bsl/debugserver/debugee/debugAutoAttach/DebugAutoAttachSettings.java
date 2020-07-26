package com.github.yukon39.bsl.debugserver.debugee.debugAutoAttach;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetType;
import lombok.Data;

@Data
public class DebugAutoAttachSettings {

    DebugTargetType[] targetType;

    String[] areaName;
}
