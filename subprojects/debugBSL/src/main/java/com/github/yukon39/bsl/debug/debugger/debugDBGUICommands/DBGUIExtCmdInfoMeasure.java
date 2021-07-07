package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugMeasure.PerformanceInfoMain;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события о получении результатов замера производительности
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement
public class DBGUIExtCmdInfoMeasure extends DBGUIExtCmdInfoBase {

    /**
     * Результаты замера производительности, закодированные в виде строки
     */
    private char[] measureStr;

    /**
     * Результаты замера производительности
     */
    private PerformanceInfoMain measure;

    public DBGUIExtCmdInfoMeasure() {
        super(DBGUIExtCmds.MEASURE_RESULT_PROCESSING);
    }
}
