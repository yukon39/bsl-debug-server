package com.github.yukon39.bsl.debug.debugger.debugRTEFilter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RteFilterItem {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private Boolean include;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    String str;
}
