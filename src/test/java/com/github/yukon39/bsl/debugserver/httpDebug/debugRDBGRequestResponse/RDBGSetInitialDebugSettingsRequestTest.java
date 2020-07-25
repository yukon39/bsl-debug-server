package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugRTEFilter.RteFilterStorage;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGSetInitialDebugSettingsRequestTest {

    @Test
    void deserializeFromFile() throws IOException, JAXBException {

        var rteProcessing = new RteFilterStorage();
        rteProcessing.setStopOnErrors(true);
        rteProcessing.setAnalyzeErrorStr(true);

        var data = new HTTPServerInitialDebugSettingsData();
        data.setBpWorkspace(new BPWorkspaceInternal());
        data.setRteProcessing(rteProcessing);

        var request = new RDBGSetInitialDebugSettingsRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.fromString("dbe7b1e9-9786-4a25-8da8-304684fa2ce3"));

        var serializer = new HTTPDebugSerializer();

        var file = new File("./src/test/resources/httpDebug/RDBGAttachDebugUIRequestTest.xml");
        var xml = Files.readAllBytes(file.toPath());

        var response = serializer.deserialize(xml, RDBGSetInitialDebugSettingsRequest.class);

        assertThat(request).isEqualTo(response);
    }
}
