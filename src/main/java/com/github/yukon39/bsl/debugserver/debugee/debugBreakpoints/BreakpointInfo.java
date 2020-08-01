package com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
public class BreakpointInfo {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Integer line;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Boolean isActive;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    String condition;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Boolean temp;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    Boolean user;
}
