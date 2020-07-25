package com.github.yukon39.bsl.debugserver.debugee.debugAutoAttach;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetType;
import lombok.Data;

@Data
public class DebugAutoAttachSettings {

    @JacksonXmlProperty(namespace = "http://v8.1c.ru/8.3/debugger/debugAutoAttach")
    @JacksonXmlElementWrapper(useWrapping = false)
    DebugTargetType[] targetType;

    @JacksonXmlProperty(namespace = "http://v8.1c.ru/8.3/debugger/debugAutoAttach")
    @JacksonXmlElementWrapper(useWrapping = false)
    String[] areaName;
}
