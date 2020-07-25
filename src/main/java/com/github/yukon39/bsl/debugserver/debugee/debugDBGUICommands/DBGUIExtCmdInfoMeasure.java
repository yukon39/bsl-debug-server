package com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands;

import com.github.yukon39.bsl.debugserver.debugee.debugMeasure.PerformanceInfoMain;
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
        super(DBGUIExtCmds.measureResultProcessing);
    }
}
