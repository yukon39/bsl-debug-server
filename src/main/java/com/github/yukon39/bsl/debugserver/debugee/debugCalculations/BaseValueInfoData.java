package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

import java.util.Date;

@Data
public class BaseValueInfoData {
    private Integer typeCode;
    private String typeName;
    private Integer valueDecimal;
    private String valueString;
    private Date valueDateTime;
    private Boolean valueBoolean;
    private char [] pres;
    private Boolean presProcessedCorrectly;
    private Boolean isExpandable;
    private Boolean isSupportIContext;
    private Boolean isIIndexedCollectionRO;
    private Boolean isSupportIEnumValue;
}
