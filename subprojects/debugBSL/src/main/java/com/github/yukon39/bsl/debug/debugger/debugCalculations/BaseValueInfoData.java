package com.github.yukon39.bsl.debug.debugger.debugCalculations;


import com.github.yukon39.bsl.debug.data.DebugValueTypeCode;
import com.github.yukon39.bsl.debug.utils.StringUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.Date;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class BaseValueInfoData {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer typeCode = DebugValueTypeCode.UNDEFINED.getTypeCode();

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String typeName;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Integer valueDecimal;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private String valueString;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private Date valueDateTime;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean valueBoolean;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private byte[] pres;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean presProcessedCorrectly;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isExpandable;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isSupportIContext;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isIIndexedCollectionRO;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugCalculations")
    private boolean isSupportIEnumValue;

    public String getPresentation() {
        return StringUtils.toString(pres);
    }
}