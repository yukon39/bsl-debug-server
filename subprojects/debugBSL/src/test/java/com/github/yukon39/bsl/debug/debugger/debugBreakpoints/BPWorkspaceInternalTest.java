package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternalTest;

public class BPWorkspaceInternalTest {

    public static BPWorkspaceInternal createTestObject() {

        var moduleId = BSLModuleIdInternalTest.createTestObjectCommonModule();

        var breakpointInfo = new BreakpointInfo();
        breakpointInfo.setLine(42);

        var moduleBPInfo = new ModuleBPInfoInternal();
        moduleBPInfo.setId(moduleId);
        moduleBPInfo.getBpInfo().add(breakpointInfo);

        var bpWorkspace = new BPWorkspaceInternal();
        bpWorkspace.getModuleBPInfo().add(moduleBPInfo);

        return bpWorkspace;
    }
}