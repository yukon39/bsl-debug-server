package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Ответ на запрос о наличии событий для клиентской части отладчика
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
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
        DBGUIExtCmdInfoValueModifyResult.class,
        DBGUIExtCmdShowMetadataObject.class
})
public class RDBGPingDebugUIResponse implements IRDBGResponse {

    /**
     * События для клиентской части отладчика
     */
    @XmlElement
    private final List<DBGUIExtCmdInfoBase> result = new ArrayList<>();
}
