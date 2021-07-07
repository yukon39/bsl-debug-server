package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import jakarta.xml.bind.annotation.XmlEnumValue;

/**
 * События поступающие в клиентскую часть отладчика
 */
public enum DBGUIExtCmds {

    /**
     * Неизвестное состояние
     */
    @XmlEnumValue("unknown")
    UNKNOWN("unknown"),

    /**
     * Начинает работу предмет отладки
     */
    @XmlEnumValue("targetStarted")
    TARGET_STARTED("targetStarted"),

    /**
     * Завершает работу предмет отладк
     */
    @XmlEnumValue("targetQuit")
    TARGET_QUIT("targetQuit"),

    /**
     * Скорректированы точки останова для модуля
     */
    @XmlEnumValue("correctedBP")
    CORRECTED_BP("correctedBP"),

    /**
     * Произошла рантайм-ошибка
     */
    @XmlEnumValue("rteProcessing")
    RTE_PROCESSING("rteProcessing"),

    /**
     * Произошла рантайм-ошибка при вычислении условия срабатывания точки останова
     */
    @XmlEnumValue("rteOnBPConditionProcessing")
    RTE_ON_BP_CONDITION_PROCESSING("rteOnBPConditionProcessing"),

    /**
     * Готовы результаты замера производительности
     */
    @XmlEnumValue("measureResultProcessing")
    MEASURE_RESULT_PROCESSING("measureResultProcessing"),

    /**
     * Сформирован стек вызова для предмета отладки
     */
    @XmlEnumValue("callStackFormed")
    CALL_STACK_FORMED("callStackFormed"),

    /**
     * Выполнено вычисление выражения
     */
    @XmlEnumValue("exprEvaluated")
    EXPR_EVALUATED("exprEvaluated"),

    /**
     * Выполнено изменение значения локальной переменной или свойства контекста
     */
    @XmlEnumValue("valueModified")
    VALUE_MODIFIED("valueModified"),

    /**
     * Передача списка ошибок
     */
    @XmlEnumValue("errorViewInfo")
    ERROR_VIEW_INFO("errorViewInfo"),

    /**
     * Установить помощник для активизации окна партнера по соединению
     */
    @XmlEnumValue("ForegroundHelperSet")
    FOREGROUND_HELPER_SET("ForegroundHelperSet"),

    /**
     * Запросить помощник для активизации окна партнера по соединению
     */
    @XmlEnumValue("ForegroundHelperRequest")
    FOREGROUND_HELPER_REQUEST("ForegroundHelperRequest"),

    /**
     * Выполнить активизацию окна партнера по соединению переданным помощником
     */
    @XmlEnumValue("ForegroundHelperProcess")
    FOREGROUND_HELPER_PROCESS("ForegroundHelperProcess"),

    /**
     * Передача имени объекта метаданных для показа
     */
    @XmlEnumValue("showMetadataObject")
    SHOW_METADATA_OBJECT("showMetadataObject")
    ;

    private final String literal;

    DBGUIExtCmds(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}