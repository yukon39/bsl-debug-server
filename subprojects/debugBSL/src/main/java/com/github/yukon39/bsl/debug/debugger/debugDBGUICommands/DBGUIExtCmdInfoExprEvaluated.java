package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события о вычислении выражения
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "DBGUIExtCmdInfoExprEvaluated")
public class DBGUIExtCmdInfoExprEvaluated extends DBGUIExtCmdInfoBase {

    /**
     * Результат вычисления
     */
    @XmlElement
    private CalculationResultBaseData evalExprResBaseData;

    public DBGUIExtCmdInfoExprEvaluated() {
        super(DBGUIExtCmds.EXPR_EVALUATED);
    }
}
