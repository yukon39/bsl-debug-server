package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;


import com.github.yukon39.bsl.debugserver.debugee.data.DebugValueTypeCode;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternalTest;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdTest;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.StackItemViewInfoData;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.*;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoExprEvaluated;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGPingDebugUIResponseTest {

    @Test
    void testSerialize() throws JAXBException {

        // given
        var request = new RDBGPingDebugUIResponse();

        var targetID = DebugTargetIdTest.createTestObjectManagedClient();

        var started = new DBGUIExtCmdInfoStarted();
        started.setTargetID(targetID);

        var quit = new DBGUIExtCmdInfoQuit();
        quit.setTargetID(targetID);

        var callstack = new DBGUIExtCmdInfoCallStackFormed();
        callstack.setStopByBP(true);

        request.getResult().add(started);
        request.getResult().add(quit);
        request.getResult().add(callstack);

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = serializer.serialize(request);
        var response = serializer.deserialize(xml, RDBGPingDebugUIResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }

    @Test
    void testDeserializeFromFile() throws IOException, JAXBException {

        // given
        var request = new RDBGPingDebugUIResponse();
        var requestResult = request.getResult();

        var targetID = DebugTargetIdTest.createTestObjectManagedClient();

        var cmdStarted = new DBGUIExtCmdInfoStarted();
        cmdStarted.setTargetID(targetID);
        requestResult.add(cmdStarted);

        var cmdQuit = new DBGUIExtCmdInfoQuit();
        cmdQuit.setTargetID(targetID);
        requestResult.add(cmdQuit);

        var callStack = new StackItemViewInfoData();
        callStack.setModuleID(BSLModuleIdInternalTest.createTestObjectCommonModule());
        callStack.setLineNo(5);

        var cmdCallStackFormed = new DBGUIExtCmdInfoCallStackFormed();
        cmdCallStackFormed.setTargetID(targetID);
        cmdCallStackFormed.setStopByBP(false);
        cmdCallStackFormed.getCallStack().add(callStack);
        requestResult.add(cmdCallStackFormed);

        var resultValueInfo = new BaseValueInfoData();
        resultValueInfo.setTypeCode(100);
        resultValueInfo.setIsExpandable(true);
        resultValueInfo.setIsSupportIContext(true);

        var propInfo = new ContextPropertyData();
        propInfo.setPropName("Cancel");
        propInfo.setIsReaded(true);

        var valueInfo = new BaseValueInfoData();
        valueInfo.setTypeCode(DebugValueTypeCode.BOOLEAN.getTypeCode());
        valueInfo.setTypeName(DebugValueTypeCode.BOOLEAN.getTypeName());
        valueInfo.setValueBoolean(false);

        var valueOfContextPropInfo = new CalculationResultContextPropertyInfo();
        valueOfContextPropInfo.setPropInfo(propInfo);
        valueOfContextPropInfo.setValueInfo(valueInfo);

        var calculationResult = new CalculationResultObjData();
        calculationResult.setViewInterface(ViewInterface.CONTEXT);
        calculationResult.getValueOfContextPropInfo().add(valueOfContextPropInfo);

        var evalExprResBaseData = new CalculationResultBaseData();
        evalExprResBaseData.setEvalResultState(CalculationResultState.CORRECTLY);
        evalExprResBaseData.setExpressionResultID(UUID.fromString("c843e036-8caf-4a3d-85d5-b96225ed17da"));
        evalExprResBaseData.setResultValueInfo(resultValueInfo);
        evalExprResBaseData.getTestedAndSupportedInterface().add(ViewInterface.NONE);
        evalExprResBaseData.setCalculationResult(calculationResult);

        var cmdCmdInfoExprEvaluated = new DBGUIExtCmdInfoExprEvaluated();
        cmdCmdInfoExprEvaluated.setTargetID(targetID);
        cmdCmdInfoExprEvaluated.setEvalExprResBaseData(evalExprResBaseData);
        requestResult.add(cmdCmdInfoExprEvaluated);

        var file = new File("./src/test/resources/httpDebug/RDBGPingDebugUIResponseTest.xml");

        // when
        var serializer = new HTTPDebugSerializer();
        var xml = Files.readAllBytes(file.toPath());
        var response = serializer.deserialize(xml, RDBGPingDebugUIResponse.class);

        // then
        var responseResult = response.getResult();
        assertThat(responseResult).hasSize(4);
        assertThat(cmdStarted).isEqualTo(responseResult.get(0));
        assertThat(cmdQuit).isEqualTo(responseResult.get(1));
        assertThat(cmdCallStackFormed).isEqualTo(responseResult.get(2));
        assertThat(cmdCmdInfoExprEvaluated).isEqualTo(responseResult.get(3));

        assertThat(request).isEqualTo(response);
    }
}
