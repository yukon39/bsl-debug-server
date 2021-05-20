package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.UtilsTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGDetachDebugUIRequestTest {

    @Test
    void testDeserializeFromFile() throws IOException, DebuggerException {

        // given
        var request = new RDBGDetachDebugUIRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.fromString("dbe7b1e9-9786-4a25-8da8-304684fa2ce3"));

        var xmlString = UtilsTest.xmlString("debugger", "debugRDBGRequestResponse", "RDBGDetachDebugUIRequestTest.xml");

        // when
        var response = DebuggerXmlSerializer.deserialize(xmlString, RDBGDetachDebugUIRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
