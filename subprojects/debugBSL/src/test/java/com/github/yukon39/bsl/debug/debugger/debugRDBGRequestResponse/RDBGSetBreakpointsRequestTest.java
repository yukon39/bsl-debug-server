package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternalTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RDBGSetBreakpointsRequestTest {

    @Test
    void testSerialize() throws DebuggerException {

        var bpWorkspace = BPWorkspaceInternalTest.createTestObject();

        var request = new RDBGSetBreakpointsRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.randomUUID());
        request.setBpWorkspace(bpWorkspace);

        var xml = DebuggerXmlSerializer.serialize(request);

        var response = DebuggerXmlSerializer.deserialize(xml, RDBGSetBreakpointsRequest.class);

        assertThat(request).isEqualTo(response);
    }

}