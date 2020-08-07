package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import com.github.yukon39.bsl.debugserver.utils.StringUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.Date;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class BaseValueInfoData {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer typeCode;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String typeName;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer valueDecimal;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String valueString;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Date valueDateTime;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean valueBoolean;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private byte[] pres;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean presProcessedCorrectly;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isExpandable;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isSupportIContext;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isIIndexedCollectionRO;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Boolean isSupportIEnumValue;

    public String getPresentation() {
        return StringUtils.toString(pres);
    }
}
