package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "DBGUIExtCmdInfoExprEvaluated")
public class DBGUIExtCmdInfoExprEvaluated extends DBGUIExtCmdInfoBase {

    @XmlElement
    private CalculationResultBaseData evalExprResBaseData;

    public DBGUIExtCmdInfoExprEvaluated() {
        super(DBGUIExtCmds.EXPR_EVALUATED);
    }
}
