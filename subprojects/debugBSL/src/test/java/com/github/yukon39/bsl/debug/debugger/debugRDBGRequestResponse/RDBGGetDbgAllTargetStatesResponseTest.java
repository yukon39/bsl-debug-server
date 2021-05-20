package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DbgTargetState;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DbgTargetStateInfo;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGGetDbgAllTargetStatesResponseTest {

    @Test
    public void testSerialize() throws DebuggerException {

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
        var xml = DebuggerXmlSerializer.serialize(request);
        var response = DebuggerXmlSerializer.deserialize(xml, RDBGGetDbgAllTargetStatesResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}
