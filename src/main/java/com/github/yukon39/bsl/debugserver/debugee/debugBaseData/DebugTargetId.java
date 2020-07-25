package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data()
@XmlAccessorType(XmlAccessType.NONE)
public class DebugTargetId extends DebugTargetIdLight {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID seanceId;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer seanceNo;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID infoBaseInstanceID;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String infoBaseAlias;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private IsServerInfoBase isServerInfoBase = IsServerInfoBase.UNDEFINED;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String userName;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String configVersion;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private DebugTargetType targetType = DebugTargetType.Unknown;
}
