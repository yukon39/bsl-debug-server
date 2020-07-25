package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdLight;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGAttachDetachDebugTargetsRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement(required = true)
    private Boolean attach;

    @XmlElement(required = true)
    private List<DebugTargetIdLight> id;
}
