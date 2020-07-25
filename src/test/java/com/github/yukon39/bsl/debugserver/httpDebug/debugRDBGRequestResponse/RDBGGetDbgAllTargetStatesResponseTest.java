package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.*;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGGetDbgAllTargetStatesResponseTest {

    @Test
    public void serialize() throws JAXBException {

//        var request = new RDBGGetDbgAllTargetStatesResponse();
//
//        var targetID = new DebugTargetId();
//        targetID.setId(UUID.randomUUID());
//        targetID.setSeanceId(UUID.randomUUID());
//        targetID.setSeanceNo(2);
//        targetID.setInfoBaseInstanceID(UUID.randomUUID());
//        targetID.setInfoBaseAlias("DefAlias");
//        targetID.setIsServerInfoBase(IsServerInfoBase.UNDEFINED);
//        targetID.setConfigVersion(UUID.randomUUID().toString());
//        targetID.setTargetType(DebugTargetType.ServerEmulation);
//
//        var targetStateInfo = new DbgTargetStateInfo();
//        targetStateInfo.setTargetID(targetID);
//        targetStateInfo.setState(DbgTargetState.Worked);
//        targetStateInfo.setStateNum(16);
//
//        request.getItem().add(targetStateInfo);
//
//        targetID = new DebugTargetId();
//        targetID.setId(UUID.randomUUID());
//        targetID.setSeanceId(UUID.randomUUID());
//        targetID.setSeanceNo(5);
//        targetID.setInfoBaseInstanceID(UUID.randomUUID());
//        targetID.setInfoBaseAlias("DefAlias");
//        targetID.setIsServerInfoBase(IsServerInfoBase.TRUE);
//        targetID.setConfigVersion(UUID.randomUUID().toString());
//        targetID.setTargetType(DebugTargetType.Server);
//
//        targetStateInfo = new DbgTargetStateInfo();
//        targetStateInfo.setTargetID(targetID);
//        targetStateInfo.setState(DbgTargetState.WaitDebugger);
//        targetStateInfo.setStateNum(5);
//
//        request.getItem().add(targetStateInfo);
//
//        var serializer = new HTTPDebugSerializer();
//
//        var xml = serializer.serialize(request);
//
//        var response = serializer.deserialize(xml, RDBGGetDbgAllTargetStatesResponse.class);
//
//        assertThat(request).isEqualTo(response);
    }


    @Test
    public void deserializeFromFile() throws IOException, JAXBException {

        var serializer = new HTTPDebugSerializer();

        var file = new File("./src/test/resources/httpDebug/RDBGGetDbgAllTargetStatesResponse.xml");
        var xml = Files.readAllBytes(file.toPath());

        var response = serializer.deserialize(xml, RDBGGetDbgAllTargetStatesResponse.class);

        var result = response.getItem();
        assertThat(result.size()).isEqualTo(2);

        var item = result.get(0);
        assertThat(item).isInstanceOf(DbgTargetStateInfo.class);
        assertThat(item.getState()).isEqualTo(DbgTargetState.Worked);
        assertThat(item.getStateNum()).isEqualTo(16);

        var targetId = item.getTargetID();
        assertThat(targetId).isInstanceOf(DebugTargetId.class);
        assertThat(targetId.getSeanceNo()).isNotNull();
    }
}
