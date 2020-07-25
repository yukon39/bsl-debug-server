package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DbgTargetStateInfo;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData", name = "response")
public class RDBGGetDbgAllTargetStatesResponse implements IRDBGResponse {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse", name = "item")
    private List<DbgTargetStateInfo> item = new ArrayList<>();
}
