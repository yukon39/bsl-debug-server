package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultBaseData;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "response", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGEvalExprResponse implements IRDBGResponse {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRDBGRequestResponse")
    private final List<CalculationResultBaseData> result = new ArrayList<>();
}
