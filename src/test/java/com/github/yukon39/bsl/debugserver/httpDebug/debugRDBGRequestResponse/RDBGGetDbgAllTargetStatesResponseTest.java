package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.*;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGGetDbgAllTargetStatesResponseTest {

    @Test
    public void testSerialize() throws JAXBException {

        // given

        var request = new RDBGGetDbgAllTargetStatesResponse();

        var targetID = DebugTargetIdTest.createTestObjectManagedClient();

        var targetStateInfo = new DbgTargetStateInfo();
        targetStateInfo.setTargetID(targetID);
        targetStateInfo.setState(DbgTargetState.WORKED);
        targetStateInfo.setStateNum(16);

        request.getItem().add(targetStateInfo);

        targetID = DebugTargetIdTest.createTestObjectServer();

        targetStateInfo = new DbgTargetStateInfo();
        targetStateInfo.setTargetID(targetID);
        targetStateInfo.setState(DbgTargetState.WAIT_DEBUGGER);
        targetStateInfo.setStateNum(5);

        request.getItem().add(targetStateInfo);

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = serializer.serialize(request);
        var response = serializer.deserialize(xml, RDBGGetDbgAllTargetStatesResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
