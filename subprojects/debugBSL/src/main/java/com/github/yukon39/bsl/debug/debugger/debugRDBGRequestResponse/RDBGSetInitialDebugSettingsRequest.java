package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGSetInitialDebugSettingsRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse", name = "data")
    private HTTPServerInitialDebugSettingsData data;
}
