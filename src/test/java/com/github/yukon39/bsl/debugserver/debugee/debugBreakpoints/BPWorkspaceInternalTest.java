package com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints;

import com.github.yukon39.bsl.debugserver.context.ModulePropertyId;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternalTest;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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