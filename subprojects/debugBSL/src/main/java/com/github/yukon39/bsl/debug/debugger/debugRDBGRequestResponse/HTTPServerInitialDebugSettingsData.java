package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugArea.DebugAreaInfo;
import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse",
        propOrder = {
                "debugAreaInfo",
                "autoAttachSettings"
        })
public class HTTPServerInitialDebugSettingsData extends HTTPInitialDebugSettingsData {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private final List<DebugAreaInfo> debugAreaInfo = new ArrayList<>();

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private DebugAutoAttachSettings autoAttachSettings;
}
