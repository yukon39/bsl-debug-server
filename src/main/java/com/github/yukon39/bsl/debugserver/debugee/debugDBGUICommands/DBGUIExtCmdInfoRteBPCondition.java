package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugRTEInfo.CheckBPCondErrorInfo;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoRteBPCondition extends DBGUIExtCmdInfoBase {

    private CheckBPCondErrorInfo errorInfo;

    public DBGUIExtCmdInfoRteBPCondition() {
        super(DBGUIExtCmds.rteOnBPConditionProcessing);
    }
}

