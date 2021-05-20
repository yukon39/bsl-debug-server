package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGSetRunTimeErrorProcessingRequest extends RDbgBaseRequest {
    private RteFilterStorage state;
}
