package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.UtilsTest;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternalTest;
import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGSetInitialDebugSettingsRequestTest {

    @Test
    void testSerialize() throws DebuggerException {

        // given
        var rteProcessing = new RteFilterStorage();
        rteProcessing.setStopOnErrors(true);
        rteProcessing.setAnalyzeErrorStr(true);

        var bpWorkspace = BPWorkspaceInternalTest.createTestObject();

        var data = new HTTPServerInitialDebugSettingsData();
        data.setBpWorkspace(bpWorkspace);
        data.setRteProcessing(rteProcessing);

        var request = new RDBGSetInitialDebugSettingsRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.randomUUID());
        request.setData(data);

        // when
        var xml = DebuggerXmlSerializer.serialize(request);
        var response = DebuggerXmlSerializer.deserialize(xml, RDBGSetInitialDebugSettingsRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }

    @Test
    void testDeserializeFromFile() throws IOException, DebuggerException {

        // given
        var rteProcessing = new RteFilterStorage();
        rteProcessing.setStopOnErrors(false);
        rteProcessing.setAnalyzeErrorStr(false);

        var data = new HTTPServerInitialDebugSettingsData();
        data.setBpWorkspace(new BPWorkspaceInternal());
        data.setRteProcessing(rteProcessing);

        var request = new RDBGSetInitialDebugSettingsRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.fromString("dbe7b1e9-9786-4a25-8da8-304684fa2ce3"));
        request.setData(data);

        var xmlString = UtilsTest.xmlString("debugger", "debugRDBGRequestResponse",
                "RDBGSetInitialDebugSettingsRequestTest.xml");

        // when
        var response = DebuggerXmlSerializer.deserialize(xmlString, RDBGSetInitialDebugSettingsRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
