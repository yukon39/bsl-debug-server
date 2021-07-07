package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetId;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Ответ на запрос получения списка предметов отладки
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGSGetDbgTargetsResponse implements IRDBGResponse {

    /**
    * Идентификаторы предметов отладки
    */
    @XmlElement(name= "id")
    private final List<DebugTargetId> id = new ArrayList<>();
}
