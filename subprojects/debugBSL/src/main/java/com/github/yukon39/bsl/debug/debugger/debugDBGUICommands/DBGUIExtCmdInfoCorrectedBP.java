package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoCorrectedBP extends DBGUIExtCmdInfoBase {
    private BPWorkspaceInternal bpWorkspace;

    public DBGUIExtCmdInfoCorrectedBP() {
        super(DBGUIExtCmds.CORRECTED_BP);
    }
}
