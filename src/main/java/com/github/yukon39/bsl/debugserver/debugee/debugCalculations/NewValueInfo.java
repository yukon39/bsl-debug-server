package com.github.yukon39.bsl.debugserver.debugee.debugCalculations;

import lombok.Data;

@Data
public class NewValueInfo {
    private NewValueVariant variant;
    private Object value;
    private String valueExpression;
}
