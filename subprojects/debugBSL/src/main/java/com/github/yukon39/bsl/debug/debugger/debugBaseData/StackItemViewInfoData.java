package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import com.github.yukon39.bsl.debug.utils.StringUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class StackItemViewInfoData {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private BSLModuleIdInternalStr moduleIDStr;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private BSLModuleIdInternal moduleID;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer lineNo;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private byte[] presentation;

    public String getUserPresentation() {
        return StringUtils.toString(presentation);
    }
}
