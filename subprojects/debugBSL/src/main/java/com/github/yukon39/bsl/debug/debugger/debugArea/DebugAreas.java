package com.github.yukon39.bsl.debug.debugger.debugArea;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Описания областей отладки (внутренний)
 */
@Data
public class DebugAreas {

    /**
     * Описания областей отладки
     */
    @XmlElement
    private final List<DebugAreaInfo> item = new ArrayList<>();
}