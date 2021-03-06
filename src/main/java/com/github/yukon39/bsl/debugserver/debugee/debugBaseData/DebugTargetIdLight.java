package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DebugTargetIdLight {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData", name = "id", required = true)
    private UUID id;
}
