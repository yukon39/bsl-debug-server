package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;


import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetType;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.IsServerInfoBase;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoBase;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGPingDebugUIResponseTest {

    @Test
    public void serialize() throws JAXBException {

        var targetID = new DebugTargetId();
        targetID.setId(UUID.randomUUID());
        targetID.setSeanceId(UUID.randomUUID());
        targetID.setSeanceNo(2);
        targetID.setInfoBaseInstanceID(UUID.randomUUID());
        targetID.setInfoBaseAlias("DefAlias");
        targetID.setIsServerInfoBase(IsServerInfoBase.UNDEFINED);
        targetID.setConfigVersion(UUID.randomUUID().toString());
        targetID.setTargetType(DebugTargetType.MANAGED_CLIENT);

        var request = new RDBGPingDebugUIResponse();

        var started = new DBGUIExtCmdInfoStarted();
        started.setTargetID(targetID);

        var quit = new DBGUIExtCmdInfoQuit();
        quit.setTargetID(targetID);

        var callstack = new DBGUIExtCmdInfoCallStackFormed();
        callstack.setStopByBP(true);

        request.getResult().add(started);
        request.getResult().add(quit);
        request.getResult().add(callstack);

        var serializer = new HTTPDebugSerializer();

        var xml = serializer.serialize(request);

        var response = serializer.deserialize(xml, RDBGPingDebugUIResponse.class);

        assertThat(request).isEqualTo(response);
    }

    @Test
    public void deserializeFromFile() throws IOException, JAXBException {

        var serializer = new HTTPDebugSerializer();

        var file = new File("./src/test/resources/httpDebug/RDBGPingDebugUIResponseTest.xml");
        var xml = Files.readAllBytes(file.toPath());

        var response = serializer.deserialize(xml, RDBGPingDebugUIResponse.class);

        var result = response.getResult();
        assertThat(result).hasSize(3);

        var start = result.get(0);
        assertThat(start).isInstanceOf(DBGUIExtCmdInfoStarted.class);

        var targetId = start.getTargetID();
        assertThat(targetId).isInstanceOf(DebugTargetId.class);
        assertThat(targetId.getInfoBaseAlias()).isEqualTo("DefAlias");
        assertThat(targetId.getId()).isEqualTo(UUID.fromString("0e215a1d-5230-42db-85f2-ca373357f81f"));
        assertThat(targetId.getSeanceNo()).isEqualTo(2);
        assertThat(targetId.getTargetType()).isEqualTo(DebugTargetType.MANAGED_CLIENT);

        var quit = result.get(1);
        assertThat(quit).isInstanceOf(DBGUIExtCmdInfoQuit.class);

        var stackformed = result.get(2);
        assertThat(stackformed).isInstanceOf(DBGUIExtCmdInfoCallStackFormed.class);
    }
}
