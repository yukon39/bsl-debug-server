package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;


import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdStr;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.Data;

/**
 * Базовый тип для описания информации о событии
 */
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
        DBGUIExtCmdInfoValueModifyResult.class,
        DBGUIExtCmdShowMetadataObject.class})
@XmlRootElement
public abstract class DBGUIExtCmdInfoBase {

    /**
     * Идентификатор события для клиента отладки (в виде числа)
     */
    @XmlElement
    private final Integer cmdIDNum;

    /**
     * Идентификатор события для клиента отладки
     */
    @XmlElement
    private final DBGUIExtCmds cmdId;

    /**
     * Идентификатор события для клиента отладки (в виде строки)
     */
    private DebugTargetIdStr targetIDStr;

    /**
     * Идентификатор предмета отладки
     */
    private DebugTargetId targetID;

    /**
     * Идентификатор события, ответом на которое является данное событие
     */
    private String requestQueueID;

    DBGUIExtCmdInfoBase() {
        this(DBGUIExtCmds.UNKNOWN);
    }

    protected DBGUIExtCmdInfoBase(DBGUIExtCmds cmdId) {
        this.cmdId = cmdId;
        this.cmdIDNum = cmdId.ordinal();
    }
}
