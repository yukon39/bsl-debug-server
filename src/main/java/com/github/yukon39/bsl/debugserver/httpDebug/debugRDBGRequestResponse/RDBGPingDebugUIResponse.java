package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

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
    private DBGUIExtCmdInfoBase[] result = new DBGUIExtCmdInfoBase[]{};
}
