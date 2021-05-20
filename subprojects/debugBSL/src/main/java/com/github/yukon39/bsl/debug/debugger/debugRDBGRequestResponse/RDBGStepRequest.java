package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugStepAction;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGStepRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement(required = true)
    private DebugTargetIdLight targetID;

    @XmlElement(required = true)
    private DebugStepAction action;

    @XmlElement
    private Boolean simple;
}
