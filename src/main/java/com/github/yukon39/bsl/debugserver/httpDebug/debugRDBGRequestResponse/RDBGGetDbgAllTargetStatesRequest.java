package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RDBGGetDbgAllTargetStatesRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement(name = "degugAreaName") // yes, degug
    private String debugAreaName;
}
