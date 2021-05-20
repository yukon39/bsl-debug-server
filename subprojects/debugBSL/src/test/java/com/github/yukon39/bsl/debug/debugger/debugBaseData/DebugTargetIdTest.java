package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import java.util.UUID;

public class DebugTargetIdTest {

    public static DebugTargetId createTestObjectManagedClient() {

        var targetID = new DebugTargetId();
        targetID.setId(UUID.fromString("0e215a1d-5230-42db-85f2-ca373357f81f"));
        targetID.setSeanceId(UUID.fromString("fdbd8b88-8a7f-4674-8f4f-945cbefe90f5"));
        targetID.setSeanceNo(2);
        targetID.setInfoBaseInstanceID(UUID.fromString("1945345b-e6a5-4183-ac6e-a6cf4e6085ed"));
        targetID.setInfoBaseAlias("DefAlias");
        targetID.setIsServerInfoBase(IsServerInfoBase.UNDEFINED);
        targetID.setConfigVersion("e6fd5c7d56a8824bbf34499f7696645f00000000");
        targetID.setTargetType(DebugTargetType.MANAGED_CLIENT);
        return  targetID;
    }

    public static DebugTargetId createTestObjectServer() {

        var targetID = new DebugTargetId();
        targetID.setId(UUID.randomUUID());
        targetID.setSeanceId(UUID.randomUUID());
        targetID.setSeanceNo(5);
        targetID.setInfoBaseInstanceID(UUID.randomUUID());
        targetID.setInfoBaseAlias("DefAlias");
        targetID.setIsServerInfoBase(IsServerInfoBase.TRUE);
        targetID.setConfigVersion(UUID.randomUUID().toString());
        targetID.setTargetType(DebugTargetType.SERVER);
        return  targetID;
    }

}