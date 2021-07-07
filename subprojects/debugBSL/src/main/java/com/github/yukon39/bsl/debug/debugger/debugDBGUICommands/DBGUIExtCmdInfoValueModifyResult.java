package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события об изменении значения локальной переменной или свойства контекста
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoValueModifyResult extends DBGUIExtCmdInfoBase{

    /**
     * Признак успешного изменения значения
     */
    private Boolean processed;

    /**
     * Описание состояния измененной локальной переменной или свойства контекста
     */
    private CalculationResultBaseData newValState;

    /**
     * Текст ошибки, если изменение значения не выполнено
     */
    private char[] errorStr;

    public DBGUIExtCmdInfoValueModifyResult() {
        super(DBGUIExtCmds.VALUE_MODIFIED);
    }
}