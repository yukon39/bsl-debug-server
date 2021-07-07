package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debug.debugger.debugForegroundData.ForegroundWindowData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Запрос на активизицию отладчика из предмета отладки
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGApplyDebuggerForegroundHelperData extends RDbgBaseRequest {

    /**
     * Идентификатор предмета отладки
     */
    private final List<DebugTargetId> targetID = new ArrayList<>();

    /**
     * Данные для активации окна
     */
    private ForegroundWindowData data;
}
