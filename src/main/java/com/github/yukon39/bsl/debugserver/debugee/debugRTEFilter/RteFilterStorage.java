package com.github.yukon39.bsl.debugserver.debugee.debugRTEFilter;

import lombok.Data;

@Data
public class RteFilterStorage {
    private Boolean stopOnErrors;
    private Boolean analyzeErrorStr;
    private RteFilterItem[] strTemplate;
}
