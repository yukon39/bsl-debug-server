package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.data.DebuggerOptions;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGAttachDebugUIRequestTest {

    @Test
    public void testSerialize() throws JAXBException {

        // given
        var request = new RDBGAttachDebugUIRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.randomUUID());

        var options = new DebuggerOptions();
        options.setForegroundAbility(true);
        request.setOptions(options);

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = serializer.serialize(request);
        var response = serializer.deserialize(xml, RDBGAttachDebugUIRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }

    @Test
    void testDeserializeFromFile() throws IOException, JAXBException {

        // given
        var options = new DebuggerOptions();
        options.setForegroundAbility(true);

        var request = new RDBGAttachDebugUIRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.fromString("dbe7b1e9-9786-4a25-8da8-304684fa2ce3"));
        request.setOptions(options);

        var file = new File("./src/test/resources/httpDebug/RDBGAttachDebugUIRequestTest.xml");

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = Files.readAllBytes(file.toPath());
        var response = serializer.deserialize(xml, RDBGAttachDebugUIRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
