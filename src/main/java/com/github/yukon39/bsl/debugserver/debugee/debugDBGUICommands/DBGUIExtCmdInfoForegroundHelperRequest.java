package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoForegroundHelperRequest extends DBGUIExtCmdInfoBase {
    private UUID senderID;

    public DBGUIExtCmdInfoForegroundHelperRequest() {
        super(DBGUIExtCmds.ForegroundHelperRequest);
    }
}
