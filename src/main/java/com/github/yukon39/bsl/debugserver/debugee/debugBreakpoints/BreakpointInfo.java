package com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints;

import lombok.Data;

@Data
public class BreakpointInfo {
    Integer line;
    Boolean isActive;
    String condition;
    Boolean temp;
    Boolean user;
}
