package com.github.yukon39.bsl.debug.debugger.debugBreakpoints;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdUser;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Информация о точках останова для модуля
 */
@Data
public class ModuleBPInfoUser {

    /**
     * Идентификатор модуля
     */
    @XmlElement
    private BSLModuleIdUser id;

    /**
     * Список точек останова
     */
    @XmlElement
    private final List<BreakpointInfo> bpInfo = new ArrayList<>();
}
