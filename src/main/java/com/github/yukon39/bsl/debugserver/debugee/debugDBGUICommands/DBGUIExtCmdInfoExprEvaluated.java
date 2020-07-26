package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoExprEvaluated extends DBGUIExtCmdInfoBase {
    private CalculationResultBaseData evalExprResBaseData;

    public DBGUIExtCmdInfoExprEvaluated() {
        super(DBGUIExtCmds.EXPR_EVALUATED);
    }
}
