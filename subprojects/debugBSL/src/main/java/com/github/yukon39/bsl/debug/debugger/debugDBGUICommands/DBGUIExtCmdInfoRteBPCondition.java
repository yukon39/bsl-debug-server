package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugRTEInfo.CheckBPCondErrorInfo;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события о произошедшей рантайм-ошибке при вычислении условия срабатывания точки останова
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoRteBPCondition extends DBGUIExtCmdInfoBase {

    /**
     * Информация об ошибке
     */
    private CheckBPCondErrorInfo errorInfo;

    public DBGUIExtCmdInfoRteBPCondition() {
        super(DBGUIExtCmds.RTE_ON_BP_CONDITION_PROCESSING);
    }
}