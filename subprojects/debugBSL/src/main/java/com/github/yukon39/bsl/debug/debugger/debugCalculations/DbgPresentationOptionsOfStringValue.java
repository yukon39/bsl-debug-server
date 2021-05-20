package com.github.yukon39.bsl.debug.debugger.debugCalculations;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DbgPresentationOptionsOfStringValue {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer maxTextSize;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean stopOnFirstEOL;

    public static DbgPresentationOptionsOfStringValue defaultOptions() {

        var options = new DbgPresentationOptionsOfStringValue();
        options.setMaxTextSize(100);
        options.setStopOnFirstEOL(true);

        return options;
    }
}
