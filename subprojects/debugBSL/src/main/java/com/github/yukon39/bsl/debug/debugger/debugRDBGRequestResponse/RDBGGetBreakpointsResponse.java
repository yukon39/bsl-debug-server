package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * Ответ на запрос для получения точек останова
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetBreakpointsResponse implements IRDBGResponse {

    /**
     * Пространство точек останова
     */
    private BPWorkspaceInternal bpWorkspace;
}
