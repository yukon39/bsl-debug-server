package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Настройки окружения для процесса отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
public class HTTPInitialDebugSettingsData {

    /**
     * Версия настроек окружения предмета отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private UUID envStateVersion;

    /**
     * Признак необходимости останова на следующей строке кода
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private Boolean breakOnNextLine;

    /**
     * Признак включенности замера производительности
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private UUID measureMode;

    /**
     * Общее время работы кода на сервере (в микросекундах), исполняемого без вызова из клиентского кода
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private Integer serverIndependentWorkTime;

    /**
     * Пространство точек останова
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private BPWorkspaceInternal bpWorkspace;

    /**
     * Версия точек останова
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private UUID bpVersion;

    /**
     * Способ обработки рантайм-ошибок
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private RteFilterStorage rteProcessing;

    /**
     * Версия настройки обработки рантайм-ошибок
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private UUID rteProcVersion;

    /**
     * Состав идентификаторов модулей, недоступных для отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private final List<BSLModuleIdInternal> inacessibleModuleID = new ArrayList<>();

    /**
     * Версия состава недоступных модулей
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private UUID inacessibleModuleVersion;
}
