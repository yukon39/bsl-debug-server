package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.core.GenericException;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoRte extends DBGUIExtCmdInfoBase {

    private GenericException exception;
    private StackItemViewInfoData[] callStack;

    public DBGUIExtCmdInfoRte() {
        super(DBGUIExtCmds.rteProcessing);
    }
}
