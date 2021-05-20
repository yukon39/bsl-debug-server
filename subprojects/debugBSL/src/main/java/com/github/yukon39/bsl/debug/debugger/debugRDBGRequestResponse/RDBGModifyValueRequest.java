package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.NewValueInfo;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGModifyValueRequest extends RDbgBaseRequest {
    private DebugTargetIdLight targetID;
    private CalculationSourceDataStorage modifyDataPath;
    private NewValueInfo newValueInfo;
    private Integer timeout;
}
