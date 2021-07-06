package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Информация о точках останова для набора модулей
 */
@Data
public class BPWorkspaceUser {

    /**
     * Информация о точках останова
     */
    @XmlElement
    private final List<ModuleBPInfoUser> moduleBPInfo = new ArrayList<>();
}
