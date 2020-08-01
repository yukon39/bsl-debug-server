package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.context.ModulePropertyId;
import com.github.yukon39.bsl.debugserver.debugee.data.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleType;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternalTest;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BreakpointInfo;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.ModuleBPInfoInternal;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugRTEFilter.RteFilterStorage;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGSetInitialDebugSettingsRequestTest {

    @Test
    void testSerialize() throws JAXBException{

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
        var serializer = new HTTPDebugSerializer();
        var xml = serializer.serialize(request);
        var response = serializer.deserialize(xml, RDBGSetInitialDebugSettingsRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }

    @Test
    void testDeserializeFromFile() throws IOException, JAXBException {

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

        var file = new File("./src/test/resources/httpDebug/RDBGSetInitialDebugSettingsRequestTest.xml");

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = Files.readAllBytes(file.toPath());
        var response = serializer.deserialize(xml, RDBGSetInitialDebugSettingsRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
