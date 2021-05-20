package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlRootElement()
@XmlAccessorType(XmlAccessType.NONE)
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
public class RDBGPingDebugUIResponse implements IRDBGResponse {

    @XmlElement
    private List<DBGUIExtCmdInfoBase> result = new ArrayList<>();
}
