package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGSetAutoAttachSettingsRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement
    private DebugAutoAttachSettings autoAttachSettings;
}


