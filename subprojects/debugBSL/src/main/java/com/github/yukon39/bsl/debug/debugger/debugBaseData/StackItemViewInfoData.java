package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import com.github.yukon39.bsl.debug.utils.StringUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

/**
 * Описание связи элемента стека вызовов с местом в модуле
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class StackItemViewInfoData {

    /**
     * Идентификатор модуля (в виде строки)
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private BSLModuleIdInternalStr moduleIDStr;

    /**
     * Идентификатор модуля
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private BSLModuleIdInternal moduleID;

    /**
     * Номер строки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer lineNo;

    /**
     * Презентация для окна стека вызовов
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private byte[] presentation;

    /**
     * "Призрачный" фрейм
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Boolean isFantom;

    public String getUserPresentation() {
        return StringUtils.toString(presentation);
    }
}
