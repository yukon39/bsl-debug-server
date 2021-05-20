package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
public class BPWorkspaceInternal {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    private final List<ModuleBPInfoInternal> moduleBPInfo = new ArrayList<>();
}
