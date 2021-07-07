package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugArea.DebugAreaInfo;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Ответ на запрос получения списка имеющихся областей отладки
 */
@Data
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetListOfDebugAreaResponse implements IRDBGResponse {

    /**
     * Запрос для установки областей отладки
     */
    @XmlElement(name = "debugAreaInfo")
    private final List<DebugAreaInfo> debugAreaInfo = new ArrayList<>();
}
