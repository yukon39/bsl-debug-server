package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class ContextPropertyData {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String propName;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isReadable;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isWritable;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isReaded;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private byte[] errorStr;
}
