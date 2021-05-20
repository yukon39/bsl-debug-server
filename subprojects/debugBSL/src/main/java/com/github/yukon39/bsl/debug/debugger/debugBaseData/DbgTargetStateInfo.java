package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DbgTargetStateInfo {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DebugTargetIdStr targetIDStr;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DebugTargetId targetID;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer stateNum = 0;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DbgTargetState state;
}
