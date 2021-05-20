package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGAttachDebugUIRequestTest {

    @Test
    public void testSerialize() throws DebuggerException {

        // given
        var request = new RDBGAttachDebugUIRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.randomUUID());

        var options = new DebuggerOptions();
        options.setForegroundAbility(true);
        request.setOptions(options);

        // when
        var serializer = new DebuggerXmlSerializer();
        var xml = serializer.serialize(request);
        var response = serializer.deserialize(xml, RDBGAttachDebugUIRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
