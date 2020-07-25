package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.AttachDebugUIResult;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGAttachDebugUIResponseTest {

    @Test
    void deserializeFromFile() throws IOException, JAXBException {

        var serializer = new HTTPDebugSerializer();

        var file = new File("./src/test/resources/httpDebug/RDBGAttachDebugUIResponseTest.xml");
        var xml = Files.readAllBytes(file.toPath());

        var response = serializer.deserialize(xml, RDBGAttachDebugUIResponse.class);

        var result = response.getResult();
        assertThat(result).isEqualTo(AttachDebugUIResult.REGISTERED);
    }
}
