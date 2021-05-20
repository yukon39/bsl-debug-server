package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGSetBreakpointsRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement
    private BPWorkspaceInternal bpWorkspace;
}
