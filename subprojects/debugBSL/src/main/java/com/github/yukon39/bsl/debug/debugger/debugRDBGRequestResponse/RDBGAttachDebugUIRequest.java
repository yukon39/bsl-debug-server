package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGAttachDebugUIRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement
    private byte[] credentials;

    @XmlElement
    private DebuggerOptions options;
}
