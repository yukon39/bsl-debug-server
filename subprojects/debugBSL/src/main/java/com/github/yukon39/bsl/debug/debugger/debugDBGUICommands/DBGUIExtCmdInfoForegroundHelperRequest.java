package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Описание события о запросе помощника для активизации окна партнера по соединению
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoForegroundHelperRequest extends DBGUIExtCmdInfoBase {

    /**
     * Идентификатор отправителя
     */
    private UUID senderID;

    public DBGUIExtCmdInfoForegroundHelperRequest() {
        super(DBGUIExtCmds.FOREGROUND_HELPER_REQUEST);
    }
}
