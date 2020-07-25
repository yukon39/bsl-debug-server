package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JsonRootName("response")
@JacksonXmlRootElement(localName = "response")
public class RDBGDetachDebugUIResponse implements IRDBGResponse {
    private Boolean result;
}
