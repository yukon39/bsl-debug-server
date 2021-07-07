package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события о завершении работы предмета отладки
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
@XmlType(name = "DBGUIExtCmdInfoQuit")
public class DBGUIExtCmdInfoQuit extends DBGUIExtCmdInfoBase {
    public DBGUIExtCmdInfoQuit() {
        super(DBGUIExtCmds.TARGET_QUIT);
    }
}
