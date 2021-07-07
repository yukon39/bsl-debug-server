package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DbgTargetStateInfo;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Ответ на запрос о состоянии всех имеющихся предметов отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetDbgAllTargetStatesResponse implements IRDBGResponse {

    /**
     * Состояния предметов отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private final List<DbgTargetStateInfo> item = new ArrayList<>();
}
