package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import jakarta.xml.bind.annotation.*;
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
