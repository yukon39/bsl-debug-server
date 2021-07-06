package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Информация о точках останова для набора модулей (внутренняя)
 */
@Data
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
public class BPWorkspaceInternal {

    /**
     * Информация о точках останова
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBreakpoints")
    private final List<ModuleBPInfoInternal> moduleBPInfo = new ArrayList<>();
}
