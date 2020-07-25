package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.*;
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
