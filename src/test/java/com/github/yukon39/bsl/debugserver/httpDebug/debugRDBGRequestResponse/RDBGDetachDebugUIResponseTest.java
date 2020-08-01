package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.AttachDebugUIResult;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGDetachDebugUIResponseTest {

    @Test
    void testDeserializeFromFile() throws IOException, JAXBException {

        // given
        var file = new File("./src/test/resources/httpDebug/RDBGDetachDebugUIResponseTest.xml");

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = Files.readAllBytes(file.toPath());
        var response = serializer.deserialize(xml, RDBGDetachDebugUIResponse.class);

        // then
        assertThat(response.getResult()).isTrue();
    }
}
