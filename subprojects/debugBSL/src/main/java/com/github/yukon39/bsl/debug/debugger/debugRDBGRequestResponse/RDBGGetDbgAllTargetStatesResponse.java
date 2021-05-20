package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DbgTargetStateInfo;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGGetDbgAllTargetStatesResponse implements IRDBGResponse {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private final List<DbgTargetStateInfo> item = new ArrayList<>();
}
