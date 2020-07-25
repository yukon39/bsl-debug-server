package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlEnumValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum DebugTargetType {

    @XmlEnumValue("Unknown")
    Unknown("Unknown"),

    @XmlEnumValue("Client")
    Client("Client"),

    @XmlEnumValue("ManagedClient")
    ManagedClient("ManagedClient"),

    @XmlEnumValue("WEBClient")
    WEBClient("WEBClient"),

    @XmlEnumValue("COMConnector")
    COMConnector("COMConnector"),

    @XmlEnumValue("Server")
    Server("Server"),

    @XmlEnumValue("ServerEmulation")
    ServerEmulation("ServerEmulation"),

    @XmlEnumValue("WEBService")
    WEBService("WEBService"),

    @XmlEnumValue("HTTPService")
    HTTPService("HTTPService"),

    @XmlEnumValue("OData")
    OData("OData"),

    @XmlEnumValue("JOB")
    JOB("JOB"),

    @XmlEnumValue("JobFileMode")
    JobFileMode("JobFileMode"),

    @XmlEnumValue("MobileClient")
    MobileClient("MobileClient"),

    @XmlEnumValue("MobileServer")
    MobileServer("MobileServer"),

    @XmlEnumValue("MobileJobFileMode")
    MobileJobFileMode("MobileJobFileMode"),

    @XmlEnumValue("MobileManagedClient")
    MobileManagedClient("MobileManagedClient");

    private final String literal;

    DebugTargetType(String literal) {
        this.literal = literal;
    }

    @Contract(value = " -> new", pure = true)
    public static DebugTargetType @NotNull [] allTargetTypes() {
        return new DebugTargetType[]{
                Client,
                ManagedClient,
                WEBClient,
                COMConnector,
                Server,
                ServerEmulation,
                WEBService,
                HTTPService,
                OData,
                JOB,
                JobFileMode,
                MobileClient,
                MobileServer,
                MobileJobFileMode,
                MobileManagedClient};
    }

    @Contract(value = " -> new", pure = true)
    public static DebugTargetType @NotNull [] defaultTargetTypes() {
        return new DebugTargetType[]{
                Client,
                ManagedClient,
                WEBClient,
                Server,
                ServerEmulation
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
