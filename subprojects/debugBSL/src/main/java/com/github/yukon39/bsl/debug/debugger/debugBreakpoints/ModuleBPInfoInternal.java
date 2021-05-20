package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
public class ModuleBPInfoInternal {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints", required = true)
    private BSLModuleIdInternal id;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    private final List<BreakpointInfo> bpInfo = new ArrayList<>();
}
