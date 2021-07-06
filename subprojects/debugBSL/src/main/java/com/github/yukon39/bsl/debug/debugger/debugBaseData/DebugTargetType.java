package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;
import org.jetbrains.annotations.Contract;

import java.util.List;

/**
 * Типы предметов отладки
 */
public enum DebugTargetType {

    /**
     * Неизвестный тип предмета отладки
     */
    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    /**
     * Предмет отладки работает на клиенте
     */
    @XmlEnumValue("Client")
    CLIENT("Client"),

    /**
     * Предмет отладки работает в рамках управляемого приложения
     */
    @XmlEnumValue("ManagedClient")
    MANAGED_CLIENT("ManagedClient"),

    /**
     * Предмет отладки работает на веб-клиенте
     */
    @XmlEnumValue("WEBClient")
    WEB_CLIENT("WEBClient"),

    /**
     * Предмет отладки работает на клиенте в COM-соединении
     */
    @XmlEnumValue("COMConnector")
    COM_CONNECTOR("COMConnector"),

    /**
     * Предмет отладки работает на сервере
     */
    @XmlEnumValue("Server")
    SERVER("Server"),

    /**
     * Предмет отладки работает на сервере (файловый вариант)
     */
    @XmlEnumValue("ServerEmulation")
    SERVER_EMULATION("ServerEmulation"),

    /**
     * Предмет отладки работает на сервере в WEB-сервисе
     */
    @XmlEnumValue("WEBService")
    WEB_SERVICE("WEBService"),

    /**
     * Предмет отладки работает на сервере в HTTP-сервисе
     */
    @XmlEnumValue("HTTPService")
    HTTP_SERVICE("HTTPService"),

    /**
     * Предмет отладки работает на сервере в стандартном интерфейсе OData
     */
    @XmlEnumValue("OData")
    O_DATA("OData"),

    /**
     * Предмет отладки работает на сервере как задание
     */
    @XmlEnumValue("JOB")
    JOB("JOB"),

    /**
     * Предмет отладки работает на сервере (файловый вариант) как задание
     */
    @XmlEnumValue("JobFileMode")
    JOB_FILE_MODE("JobFileMode"),

    /**
     * Предмет отладки работает как клиент мобильного приложения
     */
    @XmlEnumValue("MobileClient")
    MOBILE_CLIENT("MobileClient"),

    /**
     * Предмет отладки работает как сервер мобильного приложения
     */
    @XmlEnumValue("MobileServer")
    MOBILE_SERVER("MobileServer"),

    /**
     * Предмет отладки работает как задание мобильного приложения
     */
    @XmlEnumValue("MobileJobFileMode")
    MOBILE_JOB_FILE_MODE("MobileJobFileMode"),

    /**
     * Предмет оталки работате как мобильный клиент
     */
    @XmlEnumValue("MobileManagedClient")
    MOBILE_MANAGED_CLIENT("MobileManagedClient");

    private final String literal;

    DebugTargetType(String literal) {
        this.literal = literal;
    }

    @Contract(value = " -> new", pure = true)
    public static DebugTargetType [] allTargetTypes() {
        return new DebugTargetType[]{
                CLIENT,
                MANAGED_CLIENT,
                WEB_CLIENT,
                COM_CONNECTOR,
                SERVER,
                SERVER_EMULATION,
                WEB_SERVICE,
                HTTP_SERVICE,
                O_DATA,
                JOB,
                JOB_FILE_MODE,
                MOBILE_CLIENT,
                MOBILE_SERVER,
                MOBILE_JOB_FILE_MODE,
                MOBILE_MANAGED_CLIENT};
    }

    @Contract(value = " -> new", pure = true)
    public static List<DebugTargetType> defaultTargetTypes() {
        return List.of(CLIENT, MANAGED_CLIENT, WEB_CLIENT, SERVER, SERVER_EMULATION);
    }

    @Override
    public String toString() {
        return literal;
    }
}
