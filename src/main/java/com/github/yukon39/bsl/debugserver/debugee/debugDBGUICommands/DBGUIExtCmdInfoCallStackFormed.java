package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlType(name = "DBGUIExtCmdInfoCallStackFormed")
public class DBGUIExtCmdInfoCallStackFormed extends DBGUIExtCmdInfoBase {

    private Boolean stopByBP;
    private List<StackItemViewInfoData> callStack;

    public DBGUIExtCmdInfoCallStackFormed() {
        super(DBGUIExtCmds.CALL_STACK_FORMED);
    }
}
