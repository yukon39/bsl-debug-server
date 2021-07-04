package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.UtilsTest;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RDBGEvalLocalVariablesResponseTest {

    @Test
    void testDeserializeFromFile() throws IOException, DebuggerException {

        // given
        var request = new RDBGEvalLocalVariablesResponse();

        var result = new CalculationResultBaseData();
        result.setEvalResultState(CalculationResultState.CORRECTLY);
        result.setExpressionResultID(UUID.fromString("389d3855-eb30-47ea-a6c8-8bcd2881c0dc"));
        result.getTestedAndSupportedInterface().add(ViewInterface.NONE);

        var resultValueInfo = new BaseValueInfoData();
        resultValueInfo.setTypeCode(100);
        resultValueInfo.setExpandable(true);
        resultValueInfo.setSupportIContext(true);
        result.setResultValueInfo(resultValueInfo);

        var calculationResult = new CalculationResultObjData();
        calculationResult.setViewInterface(ViewInterface.CONTEXT);

        var valueOfContextPropInfo = new CalculationResultContextPropertyInfo();
        var propInfo = new ContextPropertyData();
        propInfo.setPropName("Cancel");
        propInfo.setIsReaded(true);
        valueOfContextPropInfo.setPropInfo(propInfo);

        var valueInfo = new BaseValueInfoData();
        valueInfo.setTypeCode(2); // BOOLEAN
        valueInfo.setTypeName("Boolean");
        valueInfo.setValueBoolean(false);
        valueOfContextPropInfo.setValueInfo(valueInfo);

        calculationResult.getValueOfContextPropInfo().add(valueOfContextPropInfo);

        valueOfContextPropInfo = new CalculationResultContextPropertyInfo();
        propInfo = new ContextPropertyData();
        propInfo.setPropName("Format");
        propInfo.setIsReaded(true);
        valueOfContextPropInfo.setPropInfo(propInfo);

        valueInfo = new BaseValueInfoData();
        valueInfo.setTypeCode(100);
        valueInfo.setTypeName("ValueListItem");
        valueInfo.setExpandable(true);
        valueInfo.setSupportIContext(true);
        valueOfContextPropInfo.setValueInfo(valueInfo);

        calculationResult.getValueOfContextPropInfo().add(valueOfContextPropInfo);

        result.setCalculationResult(calculationResult);

        request.setResult(result);

        var xmlString = UtilsTest.xmlString("debugger", "debugRDBGRequestResponse",
                "RDBGEvalLocalVariablesResponseTest.xml");

        // when
        var response = DebuggerXmlSerializer.deserialize(xmlString, RDBGEvalLocalVariablesResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}