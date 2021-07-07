package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.data.core.GenericException;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание события о произошедшей рантайм ошибке
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoRte extends DBGUIExtCmdInfoBase {

    /**
     * Информация об ошибке
     */
    private GenericException exception;

    /**
     * Стек вызовов
     */
    private final List<StackItemViewInfoData> callStack = new ArrayList<>();

    public DBGUIExtCmdInfoRte() {
        super(DBGUIExtCmds.RTE_PROCESSING);
    }
}
