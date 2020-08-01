package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternalTest;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RDBGSetBreakpointsRequestTest {

    @Test
    void testSerialize() throws JAXBException {

        var bpWorkspace = BPWorkspaceInternalTest.createTestObject();

        var request = new RDBGSetBreakpointsRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.randomUUID());
        request.setBpWorkspace(bpWorkspace);

        var serializer = new HTTPDebugSerializer();
        var xml = serializer.serialize(request);

        var response = serializer.deserialize(xml, RDBGSetBreakpointsRequest.class);

        assertThat(request).isEqualTo(response);
    }

}