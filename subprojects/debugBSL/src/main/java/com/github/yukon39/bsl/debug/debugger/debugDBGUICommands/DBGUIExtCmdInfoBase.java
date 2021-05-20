package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;


import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdStr;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
        this(DBGUIExtCmds.UNKNOWN);
    }

    protected DBGUIExtCmdInfoBase(DBGUIExtCmds cmdId) {
        this.cmdId = cmdId;
        this.cmdIDNum = cmdId.ordinal();
    }
}
