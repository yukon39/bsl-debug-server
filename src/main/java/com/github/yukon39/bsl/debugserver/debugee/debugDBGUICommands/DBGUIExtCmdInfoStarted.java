package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlType(name = "DBGUIExtCmdInfoStarted")
public class DBGUIExtCmdInfoStarted extends DBGUIExtCmdInfoBase {
    public DBGUIExtCmdInfoStarted() {
        super(DBGUIExtCmds.TARGET_STARTED);
    }
}
