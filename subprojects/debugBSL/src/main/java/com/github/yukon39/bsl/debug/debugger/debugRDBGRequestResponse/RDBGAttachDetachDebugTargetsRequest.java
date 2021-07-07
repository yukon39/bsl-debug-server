package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Запрос на подключение предмета отладки к отладчику
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGAttachDetachDebugTargetsRequest extends RDbgBaseRequest implements IRDBGRequest {

    /**
     * Признак подключения/отключения предмета отладки
     */
    @XmlElement(required = true)
    private Boolean attach;

    /**
     * Идентификаторы предметов отладки
     */
    @XmlElement(required = true)
    private final List<DebugTargetIdLight> id = new ArrayList<>();
}
