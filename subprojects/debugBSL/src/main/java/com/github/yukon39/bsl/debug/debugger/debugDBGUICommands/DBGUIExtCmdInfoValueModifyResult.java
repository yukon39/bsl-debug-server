package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoValueModifyResult extends DBGUIExtCmdInfoBase{

    private Boolean processed;
    private CalculationResultBaseData newValState;
    private char[] errorStr;

    public DBGUIExtCmdInfoValueModifyResult() {
        super(DBGUIExtCmds.VALUE_MODIFIED);
    }
}