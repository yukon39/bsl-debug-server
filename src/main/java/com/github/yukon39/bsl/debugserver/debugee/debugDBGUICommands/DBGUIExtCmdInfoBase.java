package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdStr;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlSeeAlso({
        DBGUIExtCmdInfoCallStackFormed.class,
        DBGUIExtCmdInfoCorrectedBP.class,
        DBGUIExtCmdInfoErrorViewInfo.class,
        DBGUIExtCmdInfoExprEvaluated.class,
        DBGUIExtCmdInfoForegroundHelperProcess.class,
        DBGUIExtCmdInfoForegroundHelperRequest.class,
        DBGUIExtCmdInfoForegroundHelperSet.class,
        DBGUIExtCmdInfoMeasure.class,
        DBGUIExtCmdInfoQuit.class,
        DBGUIExtCmdInfoRte.class,
        DBGUIExtCmdInfoRteBPCondition.class,
        DBGUIExtCmdInfoStarted.class,
        DBGUIExtCmdInfoValueModifyResult.class})
@XmlRootElement
public abstract class DBGUIExtCmdInfoBase {

    @XmlElement
    private final Integer cmdIDNum;
    @XmlElement
    private final DBGUIExtCmds cmdId;

    private DebugTargetIdStr targetIDStr;
    private DebugTargetId targetID;
    private String requestQueueID;

    DBGUIExtCmdInfoBase() {
        this(DBGUIExtCmds.unknown);
    }

    protected DBGUIExtCmdInfoBase(DBGUIExtCmds cmdId) {
        this.cmdId = cmdId;
        this.cmdIDNum = cmdId.ordinal();
    }
}
