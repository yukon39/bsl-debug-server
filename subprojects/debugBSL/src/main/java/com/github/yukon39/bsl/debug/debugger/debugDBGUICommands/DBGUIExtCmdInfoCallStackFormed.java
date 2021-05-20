package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;


import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlType(
        name = "DBGUIExtCmdInfoCallStackFormed",
        propOrder = {
                "stopByBP",
                "callStack"
        })
@XmlAccessorType(XmlAccessType.NONE)
public class DBGUIExtCmdInfoCallStackFormed extends DBGUIExtCmdInfoBase {

    @XmlElement
    private final List<StackItemViewInfoData> callStack = new ArrayList<>();

    @XmlElement
    private Boolean stopByBP;

    public DBGUIExtCmdInfoCallStackFormed() {
        super(DBGUIExtCmds.CALL_STACK_FORMED);
    }
}
