package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RDbgBaseRequest {

    @XmlElement(required = true)
    private String infoBaseAlias;

    @XmlElement(required = true)
    private UUID idOfDebuggerUI;
}
