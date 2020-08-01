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

    @Test
    public void deserializeFromFile() throws IOException, JAXBException {

        // given
        var request = new RDBGGetDbgAllTargetStatesResponse();

        var targetStateInfo = new DbgTargetStateInfo();

        var targetId = new DebugTargetId();
        targetId.setId(UUID.fromString("20f40ea7-76ce-4d56-b023-0163849e0260"));
        targetId.setSeanceId(UUID.fromString("426e45a4-61d3-4e6b-a277-8bab3f46d66c"));
        targetId.setSeanceNo(2);
        targetId.setInfoBaseInstanceID(UUID.fromString("ebac4d34-3028-4080-aa34-4019efbaf053"));
        targetId.setInfoBaseAlias("DefAlias");
        targetId.setIsServerInfoBase(IsServerInfoBase.UNDEFINED);
        targetId.setConfigVersion("d86d5f2a402de7458d2696f4fee5eace00000000");
        targetId.setTargetType(DebugTargetType.MANAGED_CLIENT);

        targetStateInfo.setTargetID(targetId);
        targetStateInfo.setStateNum(16);
        targetStateInfo.setState(DbgTargetState.WORKED);

        request.getItem().add(targetStateInfo);

        var file = new File("./src/test/resources/httpDebug/RDBGGetDbgAllTargetStatesResponse.xml");

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = Files.readAllBytes(file.toPath());
        var response = serializer.deserialize(xml, RDBGGetDbgAllTargetStatesResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
