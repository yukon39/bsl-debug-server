package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugForegroundData.ForegroundWindowData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoForegroundHelperSet extends DBGUIExtCmdInfoBase {
    private ForegroundWindowData data;

    public DBGUIExtCmdInfoForegroundHelperSet() {
        super(DBGUIExtCmds.ForegroundHelperSet);
    }
}
