package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdStr;
import com.github.yukon39.bsl.debug.debugger.debugRTEInfo.ErrorViewInfoData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание события о передаче списка ошибок
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DBGUIExtCmdInfoErrorViewInfo extends DBGUIExtCmdInfoBase {

    /**
     * Признак необходимости подключения предмета отладки в отладчике
     */
    private Boolean requestConnection;

    /**
     * Дополнительный идентификатор предмета отладки, связанного с ошибкой (строкой)
     */
    private final List<DebugTargetIdStr> addTargetIDStr = new ArrayList<>();

    /**
     * Дополнительный идентификатор предмета отладки, связанного с ошибкой
     */
    private final List<DebugTargetId> addTargetID = new ArrayList<>();

    /**
     * Информация об ошибке
     */
    private final List<ErrorViewInfoData> errorInfo = new ArrayList<>();

    public DBGUIExtCmdInfoErrorViewInfo() {
        super(DBGUIExtCmds.ERROR_VIEW_INFO);
    }
}
