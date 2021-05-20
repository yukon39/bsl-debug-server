package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGDetachDebugUIResponseTest {

    @Test
    void testDeserializeFromFile() throws IOException, DebuggerException {

        // given
        var file = new File("./src/test/resources/httpDebug/RDBGDetachDebugUIResponseTest.xml");

        // when
        var serializer = new DebuggerXmlSerializer();
        var xml = Files.readAllBytes(file.toPath());
        var response = serializer.deserialize(xml, RDBGDetachDebugUIResponse.class);

        // then
        assertThat(response.getResult()).isTrue();
    }
}
