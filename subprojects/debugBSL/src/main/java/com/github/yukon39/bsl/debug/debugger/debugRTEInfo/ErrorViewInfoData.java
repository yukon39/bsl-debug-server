package com.github.yukon39.bsl.debug.debugger.debugRTEInfo;

import com.github.yukon39.bsl.debug.data.core.GenericException;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание информации о рантайм ошибке
 */
@Data
public class ErrorViewInfoData {

    /**
     * Тип ошибки
     */
    private ErrorType errorType;

    /**
     * Исключение, породившее ошибку
     */
    private GenericException exception;

    /**
     * Идентификатор модуля
     */
    private BSLModuleIdInternal moduleID;

    /**
     * Имя модуля
     */
    private String moduleName;

    /**
     * Номер строки
     */
    private Integer lineNo;

    /**
     * Краткое описание ошибки
     */
    private char[] descr;

    /**
     * Текст исходной строки модуля
     */
    private String srcLine;

    /**
     * Позиция в строке
     */
    private Integer posInLine;

    /**
     * Представление ошибки
     */
    private char[] presentation;

    /**
     * Расширенное представление ошибки
     */
    private char[] longPresentation;

    /**
     * Стек вызовов
     */
    private final List<StackItemViewInfoData> callStack = new ArrayList<>();
}
