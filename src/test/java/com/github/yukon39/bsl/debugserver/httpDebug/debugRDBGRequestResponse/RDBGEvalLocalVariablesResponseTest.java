package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.data.DebugValueTypeCode;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.*;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RDBGEvalLocalVariablesResponseTest {

    @Test
    void testDeserializeFromFile() throws IOException, JAXBException {

        // given
        var request = new RDBGEvalLocalVariablesResponse();

        var result = new CalculationResultBaseData();
        result.setEvalResultState(CalculationResultState.CORRECTLY);
        result.setExpressionResultID(UUID.fromString("389d3855-eb30-47ea-a6c8-8bcd2881c0dc"));
        result.getTestedAndSupportedInterface().add(ViewInterface.NONE);

        var resultValueInfo = new BaseValueInfoData();
        resultValueInfo.setTypeCode(100);
        resultValueInfo.setIsExpandable(true);
        resultValueInfo.setIsSupportIContext(true);
        result.setResultValueInfo(resultValueInfo);

        var calculationResult = new CalculationResultObjData();
        calculationResult.setViewInterface(ViewInterface.CONTEXT);

        var valueOfContextPropInfo = new CalculationResultContextPropertyInfo();
        var propInfo = new ContextPropertyData();
        propInfo.setPropName("Cancel");
        propInfo.setIsReaded(true);
        valueOfContextPropInfo.setPropInfo(propInfo);

        var valueInfo = new BaseValueInfoData();
        valueInfo.setTypeCode(DebugValueTypeCode.BOOLEAN.getTypeCode());
        valueInfo.setTypeName(DebugValueTypeCode.BOOLEAN.getTypeNameRU());
        valueInfo.setValueBoolean(false);
        valueOfContextPropInfo.setValueInfo(valueInfo);

        calculationResult.getValueOfContextPropInfo().add(valueOfContextPropInfo);

        valueOfContextPropInfo = new CalculationResultContextPropertyInfo();
        propInfo = new ContextPropertyData();
        propInfo.setPropName("Format");
        propInfo.setIsReaded(true);
        valueOfContextPropInfo.setPropInfo(propInfo);

        valueInfo = new BaseValueInfoData();
        valueInfo.setTypeCode(DebugValueTypeCode.VALUE_LIST_ITEM.getTypeCode());
        valueInfo.setTypeName(DebugValueTypeCode.VALUE_LIST_ITEM.getTypeNameRU());
        valueInfo.setIsExpandable(true);
        valueInfo.setIsSupportIContext(true);
        valueOfContextPropInfo.setValueInfo(valueInfo);

        calculationResult.getValueOfContextPropInfo().add(valueOfContextPropInfo);

        result.setCalculationResult(calculationResult);

        request.setResult(result);

        var file = new File("./src/test/resources/httpDebug/RDBGEvalLocalVariablesResponseTest.xml");

        // when
        var xml = Files.readAllBytes(file.toPath());
        var serializer = new HTTPDebugSerializer();
        var response = serializer.deserialize(xml, RDBGEvalLocalVariablesResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}