package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.UtilsTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGDetachDebugUIResponseTest {

    @Test
    void testDeserializeFromFile() throws IOException, DebuggerException {

        // given
        var xmlString = UtilsTest.xmlString("debugger", "debugRDBGRequestResponse", "RDBGDetachDebugUIResponseTest.xml");

        // when
        var response = DebuggerXmlSerializer.deserialize(xmlString, RDBGDetachDebugUIResponse.class);

        // then
        assertThat(response.getResult()).isTrue();
    }
}
