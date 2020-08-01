package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;


import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.*;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoBase;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGPingDebugUIResponseTest {

    @Test
    void testSerialize() throws JAXBException {

        // given
        var request = new RDBGPingDebugUIResponse();

        var targetID = DebugTargetIdTest.createTestObjectManagedClient();

        var started = new DBGUIExtCmdInfoStarted();
        started.setTargetID(targetID);

        var quit = new DBGUIExtCmdInfoQuit();
        quit.setTargetID(targetID);

        var callstack = new DBGUIExtCmdInfoCallStackFormed();
        callstack.setStopByBP(true);

        request.getResult().add(started);
        request.getResult().add(quit);
        request.getResult().add(callstack);

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = serializer.serialize(request);
        var response = serializer.deserialize(xml, RDBGPingDebugUIResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }

    @Test
    void testDeserializeFromFile() throws IOException, JAXBException {

        // given
        var request = new RDBGPingDebugUIResponse();

        var targetID = DebugTargetIdTest.createTestObjectManagedClient();

        var cmdStarted = new DBGUIExtCmdInfoStarted();
        cmdStarted.setTargetID(targetID);
        request.getResult().add(cmdStarted);

        var cmdQuit = new DBGUIExtCmdInfoQuit();
        cmdQuit.setTargetID(targetID);
        request.getResult().add(cmdQuit);

        var callStack = new StackItemViewInfoData();
        callStack.setModuleID(BSLModuleIdInternalTest.createTestObjectCommonModule());
        callStack.setLineNo(5);

        var cmdCallStackFormed = new DBGUIExtCmdInfoCallStackFormed();
        cmdCallStackFormed.setTargetID(targetID);
        cmdCallStackFormed.setStopByBP(false);
        cmdCallStackFormed.getCallStack().add(callStack);
        request.getResult().add(cmdCallStackFormed);

        var file = new File("./src/test/resources/httpDebug/RDBGPingDebugUIResponseTest.xml");

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = Files.readAllBytes(file.toPath());
        var response = serializer.deserialize(xml, RDBGPingDebugUIResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
