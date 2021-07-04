package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;


import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.UtilsTest;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternalTest;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdTest;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.StackItemViewInfoData;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.*;
import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.DBGUIExtCmdInfoCallStackFormed;
import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.DBGUIExtCmdInfoExprEvaluated;
import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.DBGUIExtCmdInfoQuit;
import com.github.yukon39.bsl.debug.debugger.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RDBGPingDebugUIResponseTest {

    @Test
    void testSerialize() throws DebuggerException {

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
        var xml = DebuggerXmlSerializer.serialize(request);
        var response = DebuggerXmlSerializer.deserialize(xml, RDBGPingDebugUIResponse.class);

        // then
        assertThat(request).isEqualTo(response);
    }

    @Test
    void testDeserializeFromFile() throws IOException, DebuggerException {

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
        resultValueInfo.setExpandable(true);
        resultValueInfo.setSupportIContext(true);

        var propInfo = new ContextPropertyData();
        propInfo.setPropName("Cancel");
        propInfo.setIsReaded(true);

        var valueInfo = new BaseValueInfoData();
        valueInfo.setTypeCode(2); // BOOLEAN
        valueInfo.setTypeName("Boolean");
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

        var xmlString = UtilsTest.xmlString("debugger", "debugRDBGRequestResponse",
                "RDBGPingDebugUIResponseTest.xml");

        // when
        var response = DebuggerXmlSerializer.deserialize(xmlString, RDBGPingDebugUIResponse.class);

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
