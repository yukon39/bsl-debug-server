package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.data.core.GenericException;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
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
        super(DBGUIExtCmds.RTE_PROCESSING);
    }
}
