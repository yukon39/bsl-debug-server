package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum DebugTargetType {

    @XmlEnumValue("Unknown")
    UNKNOWN("Unknown"),

    @XmlEnumValue("Client")
    CLIENT("Client"),

    @XmlEnumValue("ManagedClient")
    MANAGED_CLIENT("ManagedClient"),

    @XmlEnumValue("WEBClient")
    WEB_CLIENT("WEBClient"),

    @XmlEnumValue("COMConnector")
    COM_CONNECTOR("COMConnector"),

    @XmlEnumValue("Server")
    SERVER("Server"),

    @XmlEnumValue("ServerEmulation")
    SERVER_EMULATION("ServerEmulation"),

    @XmlEnumValue("WEBService")
    WEB_SERVICE("WEBService"),

    @XmlEnumValue("HTTPService")
    HTTP_SERVICE("HTTPService"),

    @XmlEnumValue("OData")
    O_DATA("OData"),

    @XmlEnumValue("JOB")
    JOB("JOB"),

    @XmlEnumValue("JobFileMode")
    JOB_FILE_MODE("JobFileMode"),

    @XmlEnumValue("MobileClient")
    MOBILE_CLIENT("MobileClient"),

    @XmlEnumValue("MobileServer")
    MOBILE_SERVER("MobileServer"),

    @XmlEnumValue("MobileJobFileMode")
    MOBILE_JOB_FILE_MODE("MobileJobFileMode"),

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
    public static DebugTargetType [] defaultTargetTypes() {
        return new DebugTargetType[]{
                CLIENT,
                MANAGED_CLIENT,
                WEB_CLIENT,
                SERVER,
                SERVER_EMULATION
        };
    }

    @Override
    public String toString() {
        return literal;
    }

    public String getLocalizedString(Locale locale) {
        return "";
    }
}
