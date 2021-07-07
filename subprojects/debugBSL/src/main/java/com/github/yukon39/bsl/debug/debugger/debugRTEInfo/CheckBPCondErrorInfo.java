package com.github.yukon39.bsl.debug.debugger.debugRTEInfo;

import com.github.yukon39.bsl.debug.data.core.GenericException;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import lombok.Data;

/**
 * Описание информации об ошибке при проверке условия срабатывания точки останова
 */
@Data
public class CheckBPCondErrorInfo {

    /**
     * Условие срабатывания точки останова
     */
    private String bpCondition;

    /**
     * Исключение, породившее ошибку
     */
    private GenericException exception;

    /**
     * Идентификатор модуля
     */
    private BSLModuleIdInternal moduleID;

    /**
     * Номер строки
     */
    private Integer lineNo;
}
