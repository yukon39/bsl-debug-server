package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugMeasure.PerformanceInfoMain;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoMeasure extends DBGUIExtCmdInfoBase {

    private char[] measureStr;
    private PerformanceInfoMain measure;

    public DBGUIExtCmdInfoMeasure() {
        super(DBGUIExtCmds.MEASURE_RESULT_PROCESSING);
    }
}
