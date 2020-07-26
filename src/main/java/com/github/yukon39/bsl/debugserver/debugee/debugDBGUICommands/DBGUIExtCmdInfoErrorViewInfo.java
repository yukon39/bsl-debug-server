package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdStr;
import com.github.yukon39.bsl.debugserver.debugee.debugRTEInfo.ErrorViewInfoData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoErrorViewInfo extends DBGUIExtCmdInfoBase {

    private Boolean requestConnection;
    private DebugTargetIdStr[] addTargetIDStr;
    private DebugTargetId[] addTargetID;
    private ErrorViewInfoData[] errorInfo;

    public DBGUIExtCmdInfoErrorViewInfo() {
        super(DBGUIExtCmds.ERROR_VIEW_INFO);
    }
}
