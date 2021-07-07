package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события об активизации окна партнера по соединению переданным помощником
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoForegroundHelperProcess extends DBGUIExtCmdInfoBase {

    /**
     * Данные для активации окна
     */
    private ForegroundWindowData data;

    public DBGUIExtCmdInfoForegroundHelperProcess() {
        super(DBGUIExtCmds.FOREGROUND_HELPER_PROCESS);
    }
}
