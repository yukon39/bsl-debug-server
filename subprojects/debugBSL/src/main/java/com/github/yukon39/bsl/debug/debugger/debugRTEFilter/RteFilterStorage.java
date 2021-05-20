package com.github.yukon39.bsl.debug.debugger.debugRTEFilter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {
        "stopOnErrors",
        "analyzeErrorStr",
        "strTemplate"
})
public class RteFilterStorage {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private final List<RteFilterItem> strTemplate = new ArrayList<>();

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private Boolean stopOnErrors = false;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private Boolean analyzeErrorStr = false;
}
