package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.AttachDebugUIResult;
import lombok.Data;

@Data
@JsonRootName("response")
@JacksonXmlRootElement(localName = "response")
public class RDBGAttachDebugUIResponse implements IRDBGResponse {
    private AttachDebugUIResult result;
}
