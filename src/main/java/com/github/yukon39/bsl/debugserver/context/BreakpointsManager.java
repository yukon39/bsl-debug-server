package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BreakpointInfo;
import org.eclipse.lsp4j.debug.Breakpoint;

import java.util.HashMap;
import java.util.Map;

public class BreakpointsManager {

    private final Map<BreakpointInfo, Breakpoint> map = new HashMap<>();

}
