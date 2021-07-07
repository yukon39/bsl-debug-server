package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание события о формировании стека вызовов
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlType(name = "DBGUIExtCmdInfoCallStackFormed")
@XmlAccessorType(XmlAccessType.NONE)
public class DBGUIExtCmdInfoCallStackFormed extends DBGUIExtCmdInfoBase {

    /**
     * Признак того, что приложение остановлено по точке останова
     * Признак будет иметь состояние Истина, в одном из следующих случаев:
     * * сработала точка останова;
     * * выполнен шаг при пошаговой отладке (для того предмета отладки, в котором был инициирован шаг);
     * * отрабатывается останов по команде "отладка - остановить" (для всех останавливаемых предметов отладки)
     */
    @XmlElement
    private Boolean stopByBP;

    /**
     * Стек вызовов
     */
    @XmlElement
    private final List<StackItemViewInfoData> callStack = new ArrayList<>();

    public DBGUIExtCmdInfoCallStackFormed() {
        super(DBGUIExtCmds.CALL_STACK_FORMED);
    }
}
