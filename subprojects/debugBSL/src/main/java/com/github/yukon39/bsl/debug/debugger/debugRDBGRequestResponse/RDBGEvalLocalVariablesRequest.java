package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RDBGEvalLocalVariablesRequest extends RDbgBaseRequest implements IRDBGRequest {

    @XmlElement
    private Integer calcWaitingTime;

    @XmlElement
    private DebugTargetIdLight targetID;

    @XmlElement
    private final List<CalculationSourceDataStorage> expr = new ArrayList<>();
}
