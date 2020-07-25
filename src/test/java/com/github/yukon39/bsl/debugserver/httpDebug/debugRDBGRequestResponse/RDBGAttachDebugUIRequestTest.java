package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.AttachDebugUIResult;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGAttachDebugUIRequestTest {

    @Test
    public void serialize() throws JAXBException {

        var request = new RDBGAttachDebugUIRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.randomUUID());

        var serializer = new HTTPDebugSerializer();

        var xml = serializer.serialize(request);

        var response = serializer.deserialize(xml, RDBGAttachDebugUIRequest.class);

        assertThat(request).isEqualTo(response);
    }

    @Test
    void deserializeFromFile() throws IOException, JAXBException {

        var options = new DebuggerOptions();
        options.setForegroundAbility(true);

        var request = new RDBGAttachDebugUIRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.fromString("dbe7b1e9-9786-4a25-8da8-304684fa2ce3"));
        request.setOptions(options);

        var serializer = new HTTPDebugSerializer();

        var file = new File("./src/test/resources/httpDebug/RDBGAttachDebugUIRequestTest.xml");
        var xml = Files.readAllBytes(file.toPath());

        var response = serializer.deserialize(xml, RDBGAttachDebugUIRequest.class);

        assertThat(request).isEqualTo(response);
    }
}
