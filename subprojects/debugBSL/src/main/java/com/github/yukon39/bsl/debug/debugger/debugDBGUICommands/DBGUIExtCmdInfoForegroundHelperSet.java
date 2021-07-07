package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события об установке помощника для активизации окна партнера по соединению
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoForegroundHelperSet extends DBGUIExtCmdInfoBase {

    /**
     * Данные для активации окна
     */
    private ForegroundWindowData data;

    public DBGUIExtCmdInfoForegroundHelperSet() {
        super(DBGUIExtCmds.FOREGROUND_HELPER_SET);
    }
}
