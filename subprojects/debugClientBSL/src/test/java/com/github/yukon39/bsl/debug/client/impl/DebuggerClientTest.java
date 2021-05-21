package com.github.yukon39.bsl.debug.client.impl;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.data.DebugValueTypeCode;
import com.github.yukon39.bsl.debug.debugger.debugAutoAttach.DebugAutoAttachSettings;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.AttachDebugUIResult;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetType;
import com.github.yukon39.bsl.debug.debugger.debugBreakpoints.BPWorkspaceInternal;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.*;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.*;
import com.github.yukon39.bsl.debug.debugger.debugRTEFilter.RteFilterStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class DebuggerClientTest {

    private DebuggerClientMock httpClient;

    @BeforeEach
    void beforeEach() {
        httpClient = new DebuggerClientMock();
        httpClient.setInfobaseAlias("DefAlias");
        httpClient.setDebugSession(UUID.fromString("dbe7b1e9-9786-4a25-8da8-304684fa2ce3"));
    }

    @Test
    void testGetApiVersion() throws ExecutionException, InterruptedException {

        //given
        var responseFile = new File("./src/test/resources/httpDebug/MiscRDbgGetAPIVerResponse.xml");

        httpClient.setResponseFile(responseFile);

        var request = new MiscRDbgGetAPIVerRequest();
        var response = new MiscRDbgGetAPIVerResponse();

        response.setVersion("8.3.17");

        // when
        var result = httpClient.getApiVersion().get();

        // then
        assertThat(result).isEqualTo("8.3.17");
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testTest() throws ExecutionException, InterruptedException {

        //given
        var request = new RDBGTestRequest();
        var response = new RDBGEmptyResponse();

        // when
        var result = httpClient.test().get();

        // then
        assertThat(result).isNull();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testAttach() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGAttachDebugUIRequestTest.xml");
        var responseFile = new File("./src/test/resources/httpDebug/RDBGAttachDebugUIResponseTest.xml");

        httpClient.setResponseFile(responseFile);

        var request = httpClient.readRequest(requestFile, RDBGAttachDebugUIRequest.class);
        var response = new RDBGAttachDebugUIResponse();

        var credentialsString = "";
        var credentials = credentialsString.toCharArray();
        var options = new DebuggerOptions();
        options.setForegroundAbility(true);

        response.setResult(AttachDebugUIResult.REGISTERED);

        // when
        var result = httpClient.attach(credentials, options).get();

        // then
        assertThat(result).isEqualTo(AttachDebugUIResult.REGISTERED);
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testDetach() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGDetachDebugUIRequestTest.xml");
        var responseFile = new File("./src/test/resources/httpDebug/RDBGDetachDebugUIResponseTest.xml");

        httpClient.setResponseFile(responseFile);

        var request = httpClient.readRequest(requestFile, RDBGDetachDebugUIRequest.class);
        var response = new RDBGDetachDebugUIResponse();

        response.setResult(true);

        // when
        var result = httpClient.detach().get();

        // then
        assertThat(result).isTrue();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testStartUpdateIB() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGStartUpdateIBRequest.xml");
        var responseFile = new File("./src/test/resources/httpDebug/RDBGStartUpdateIBResponse.xml");

        httpClient.setResponseFile(responseFile);

        var request = httpClient.readRequest(requestFile, RDBGStartUpdateIBRequest.class);
        var response = new RDBGStartUpdateIBResponse();

        response.setResult(true);

        // when
        var result = httpClient.startUpdateIB().get();

        // then
        assertThat(result).isTrue();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testFinishUpdateIB() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGFinishUpdateIBRequest.xml");
        var responseFile = new File("./src/test/resources/httpDebug/RDBGFinishUpdateIBResponse.xml");

        httpClient.setResponseFile(responseFile);

        var request = httpClient.readRequest(requestFile, RDBGFinishUpdateIBRequest.class);
        var response = new RDBGFinishUpdateIBResponse();

        response.setResult(true);

        // when
        var result = httpClient.finishUpdateIB().get();

        // then
        assertThat(result).isTrue();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testGetInaccessibleModules() {

    }

    @Test
    void testSetInaccessibleModules() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGSetInaccessibleModulesRequest.xml");

        var request = httpClient.readRequest(requestFile, RDBGSetInaccessibleModulesRequest.class);
        var response = new RDBGEmptyResponse();

        var modules = new ArrayList<BSLModuleIdInternal>();

        // when
        var result = httpClient.setInaccessibleModules(modules).get();

        // then
        assertThat(result).isNull();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testInitSettings() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGSetInitialDebugSettingsRequestTest.xml");

        var request = httpClient.readRequest(requestFile, RDBGSetInitialDebugSettingsRequest.class);
        var response = new RDBGEmptyResponse();

        var data = new HTTPServerInitialDebugSettingsData();

        var bpWorkspace = new BPWorkspaceInternal();
        data.setBpWorkspace(bpWorkspace);

        var rteProcessing = new RteFilterStorage();
        data.setRteProcessing(rteProcessing);

        // when
        var result = httpClient.initSettings(data).get();

        // then
        assertThat(result).isNull();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testSetAutoAttachSettings() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGSetAutoAttachSettingsRequest.xml");

        var request = httpClient.readRequest(requestFile, RDBGSetAutoAttachSettingsRequest.class);
        var response = new RDBGEmptyResponse();

        var settings = new DebugAutoAttachSettings();
        settings.getTargetType().addAll(DebugTargetType.defaultTargetTypes());

        // when
        var result = httpClient.setAutoAttachSettings(settings).get();

        // then
        assertThat(result).isNull();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testAttachDetachDebugTargets() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGAttachDetachDebugTargetsRequest.xml");

        var request = httpClient.readRequest(requestFile, RDBGAttachDetachDebugTargetsRequest.class);
        var response = new RDBGEmptyResponse();

        var targetId = new DebugTargetIdLight(UUID.fromString("f8849103-dbcd-4984-905d-28059c33a720"));

        // when
        var result = httpClient.attachDetachDebugTargets(true, List.of(targetId)).get();

        // then
        assertThat(result).isNull();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

//    @Test
//    void testTerminateDebugTargets() throws ExecutionException, InterruptedException, DebuggerException, IOException {
//
//        //given
//        var requestFile = new File("./src/test/resources/httpDebug/RDBGTerminateRequest.xml");
//
//        var request = httpClient.readRequest(requestFile, RDBGTerminateRequest.class);
//        var response = new RDBGTerminateResponse();
//
//        var targetId = DebugTargetIdTest.createTestObjectManagedClient();
//
//        // when
//        var result = httpClient.terminateDebugTargets(List.of(targetId)).get();
//
//        // then
//        assertThat(request).isEqualTo(httpClient.getRequest());
//        assertThat(response).isEqualTo(httpClient.getResponse());
//        assertThat(result).isNull();
//    }

//    @Test
//    void testGetAllTargetStates() throws ExecutionException, InterruptedException, DebuggerException, IOException {
//
//        //given
//        var requestFile = new File("./src/test/resources/httpDebug/RDBGGetDbgAllTargetStatesRequest.xml");
//        var responseFile = new File("./src/test/resources/httpDebug/RDBGGetDbgAllTargetStatesResponse.xml");
//
//        httpClient.setResponseFile(responseFile);
//
//        var request = httpClient.readRequest(requestFile, RDBGGetDbgAllTargetStatesRequest.class);
//        var response = new RDBGGetDbgAllTargetStatesResponse();
//
//        var targetID = DebugTargetIdTest.createTestObjectManagedClient();
//
//        var targetStateInfoWorked = new DbgTargetStateInfo();
//        targetStateInfoWorked.setTargetID(targetID);
//        targetStateInfoWorked.setState(DbgTargetState.WORKED);
//        targetStateInfoWorked.setStateNum(16);
//
//        response.getItem().add(targetStateInfoWorked);
//
//        // when
//        var result = httpClient.getAllTargetStates().get();
//
//        // then
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0)).isEqualTo(targetStateInfoWorked);
//
//        assertThat(request).isEqualTo(httpClient.getRequest());
//        assertThat(response).isEqualTo(httpClient.getResponse());
//    }

    @Test
    void testGetTargetState() {

    }

//    @Test
//    void testPing() throws ExecutionException, InterruptedException {
//
//        //given
//        var responseFile = new File("./src/test/resources/httpDebug/RDBGPingDebugUIResponseTest.xml");
//
//        httpClient.setResponseFile(responseFile);
//
//        var request = new RDBGPingDebugUIRequest();
//        var response = new RDBGPingDebugUIResponse();
//
//        var targetID = DebugTargetIdTest.createTestObjectManagedClient();
//
//        var cmdStarted = new DBGUIExtCmdInfoStarted();
//        cmdStarted.setTargetID(targetID);
//        response.getResult().add(cmdStarted);
//
//        var cmdQuit = new DBGUIExtCmdInfoQuit();
//        cmdQuit.setTargetID(targetID);
//        response.getResult().add(cmdQuit);
//
//        var callStack = new StackItemViewInfoData();
//        callStack.setModuleID(BSLModuleIdInternalTest.createTestObjectCommonModule());
//        callStack.setLineNo(5);
//
//        var cmdCallStackFormed = new DBGUIExtCmdInfoCallStackFormed();
//        cmdCallStackFormed.setTargetID(targetID);
//        cmdCallStackFormed.setStopByBP(false);
//        cmdCallStackFormed.getCallStack().add(callStack);
//        response.getResult().add(cmdCallStackFormed);
//
//        var resultValueInfo = new BaseValueInfoData();
//        resultValueInfo.setTypeCode(100);
//        resultValueInfo.setExpandable(true);
//        resultValueInfo.setSupportIContext(true);
//
//        var propInfo = new ContextPropertyData();
//        propInfo.setPropName("Cancel");
//        propInfo.setIsReaded(true);
//
//        var valueInfo = new BaseValueInfoData();
//        valueInfo.setTypeCode(DebugValueTypeCode.BOOLEAN.getTypeCode());
//        valueInfo.setTypeName(DebugValueTypeCode.BOOLEAN.getTypeName());
//        valueInfo.setValueBoolean(false);
//
//        var valueOfContextPropInfo = new CalculationResultContextPropertyInfo();
//        valueOfContextPropInfo.setPropInfo(propInfo);
//        valueOfContextPropInfo.setValueInfo(valueInfo);
//
//        var calculationResult = new CalculationResultObjData();
//        calculationResult.setViewInterface(ViewInterface.CONTEXT);
//        calculationResult.getValueOfContextPropInfo().add(valueOfContextPropInfo);
//
//        var evalExprResBaseData = new CalculationResultBaseData();
//        evalExprResBaseData.setEvalResultState(CalculationResultState.CORRECTLY);
//        evalExprResBaseData.setExpressionResultID(UUID.fromString("c843e036-8caf-4a3d-85d5-b96225ed17da"));
//        evalExprResBaseData.setResultValueInfo(resultValueInfo);
//        evalExprResBaseData.getTestedAndSupportedInterface().add(ViewInterface.NONE);
//        evalExprResBaseData.setCalculationResult(calculationResult);
//
//        var cmdCmdInfoExprEvaluated = new DBGUIExtCmdInfoExprEvaluated();
//        cmdCmdInfoExprEvaluated.setTargetID(targetID);
//        cmdCmdInfoExprEvaluated.setEvalExprResBaseData(evalExprResBaseData);
//        response.getResult().add(cmdCmdInfoExprEvaluated);
//
//        // when
//        var result = httpClient.ping().get();
//
//        // then
//        assertThat(result).hasSize(4);
//        assertThat(cmdStarted).isEqualTo(result.get(0));
//        assertThat(cmdQuit).isEqualTo(result.get(1));
//        assertThat(cmdCallStackFormed).isEqualTo(result.get(2));
//        assertThat(cmdCmdInfoExprEvaluated).isEqualTo(result.get(3));
//
//        assertThat(request).isEqualTo(httpClient.getRequest());
//        assertThat(response).isEqualTo(httpClient.getResponse());
//    }

    @Test
    void testSetBreakOnNextStatement() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGSetBreakOnNextStatementRequest.xml");

        var request = httpClient.readRequest(requestFile, RDBGSetBreakOnNextStatementRequest.class);
        var response = new RDBGEmptyResponse();

        // when
        var result = httpClient.setBreakOnNextStatement().get();

        // then
        assertThat(result).isNull();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testClearBreakOnNextStatement() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGClearBreakOnNextStatementRequest.xml");

        var request = httpClient.readRequest(requestFile, RDBGClearBreakOnNextStatementRequest.class);
        var response = new RDBGEmptyResponse();

        // when
        var result = httpClient.clearBreakOnNextStatement().get();

        // then
        assertThat(result).isNull();
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

//    @Test
//    void testSetBreakpoints() throws ExecutionException, InterruptedException, DebuggerException, IOException {
//
//        //given
//        var requestFile = new File("./src/test/resources/httpDebug/RDBGSetBreakpointsRequest.xml");
//
//        var request = httpClient.readRequest(requestFile, RDBGSetBreakpointsRequest.class);
//        var response = new RDBGSetBreakpointsResponse();
//
//        var workspaceBP = BPWorkspaceInternalTest.createTestObject();
//
//        // when
//        var result = httpClient.setBreakpoints(workspaceBP).get();
//
//        // then
//        assertThat(result).isNull();
//        assertThat(request).isEqualTo(httpClient.getRequest());
//        assertThat(response).isEqualTo(httpClient.getResponse());
//    }

//    @Test
//    void testStep() throws ExecutionException, InterruptedException, DebuggerException, IOException {
//
//        //given
//        var requestFile = new File("./src/test/resources/httpDebug/RDBGStepRequest.xml");
//        var responseFile = new File("./src/test/resources/httpDebug/RDBGStepResponse.xml");
//
//        httpClient.setResponseFile(responseFile);
//
//        var request = httpClient.readRequest(requestFile, RDBGStepRequest.class);
//        var response = new RDBGStepResponse();
//
//        var targetID = DebugTargetIdTest.createTestObjectManagedClient();
//
//        var targetStateInfoWorked = new DbgTargetStateInfo();
//        targetStateInfoWorked.setTargetID(targetID);
//        targetStateInfoWorked.setState(DbgTargetState.WORKED);
//        targetStateInfoWorked.setStateNum(16);
//
//        response.getItem().add(targetStateInfoWorked);
//
//        var targetIdLight = new DebugTargetIdLight();
//        targetIdLight.setId(UUID.fromString("f304a4c5-2287-4fa6-86bf-e2827dd6b744"));
//
//        var action = DebugStepAction.STEP_IN;
//        var isSimple = false;
//
//        // when
//        var result = httpClient.step(targetIdLight, action, isSimple).get();
//
//        // then
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0)).isEqualTo(targetStateInfoWorked);
//
//        assertThat(request).isEqualTo(httpClient.getRequest());
//        assertThat(response).isEqualTo(httpClient.getResponse());
//    }

//    @Test
//    void testGetCallStack() throws ExecutionException, InterruptedException, DebuggerException, IOException {
//
//        //given
//        var requestFile = new File("./src/test/resources/httpDebug/RDBGGetCallStackRequest.xml");
//        var responseFile = new File("./src/test/resources/httpDebug/RDBGGetCallStackResponse.xml");
//
//        httpClient.setResponseFile(responseFile);
//
//        var request = httpClient.readRequest(requestFile, RDBGGetCallStackRequest.class);
//        var response = new RDBGGetCallStackResponse();
//
//        var stackItem = new StackItemViewInfoData();
//        stackItem.setModuleID(BSLModuleIdInternalTest.createTestObjectCatalogManagerModule());
//        stackItem.setLineNo(5);
//
//        response.getCallStack().add(stackItem);
//
//        var targetIdLight = new DebugTargetIdLight(UUID.fromString("bcc00f46-e891-4897-83e4-93cdbd629e10"));
//
//        // when
//        var result = httpClient.getCallStack(targetIdLight).get();
//
//        // then
//        assertThat(result).hasSize(1);
//        assertThat(result.get(0)).isEqualTo(stackItem);
//
//        assertThat(request).isEqualTo(httpClient.getRequest());
//        assertThat(response).isEqualTo(httpClient.getResponse());
//    }

    @Test
    void testEvalLocalVariables() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGEvalLocalVariablesRequestTest.xml");
        var responseFile = new File("./src/test/resources/httpDebug/RDBGEvalLocalVariablesResponseTest.xml");

        httpClient.setResponseFile(responseFile);

        var request = httpClient.readRequest(requestFile, RDBGEvalLocalVariablesRequest.class);
        var response = new RDBGEvalLocalVariablesResponse();

        var responseResult = new CalculationResultBaseData();
        responseResult.setEvalResultState(CalculationResultState.CORRECTLY);
        responseResult.setExpressionResultID(UUID.fromString("389d3855-eb30-47ea-a6c8-8bcd2881c0dc"));
        responseResult.getTestedAndSupportedInterface().add(ViewInterface.NONE);

        var resultValueInfo = new BaseValueInfoData();
        resultValueInfo.setTypeCode(100);
        resultValueInfo.setExpandable(true);
        resultValueInfo.setSupportIContext(true);
        responseResult.setResultValueInfo(resultValueInfo);

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
        valueInfo.setExpandable(true);
        valueInfo.setSupportIContext(true);
        valueOfContextPropInfo.setValueInfo(valueInfo);

        calculationResult.getValueOfContextPropInfo().add(valueOfContextPropInfo);

        responseResult.setCalculationResult(calculationResult);

        response.setResult(responseResult);

        var targetIdLight = new DebugTargetIdLight(UUID.fromString("3f80453e-0fa5-4ebd-9400-09bf96d0939f"));
        var expression = new CalculationSourceDataStorage();
        var waitTime = 100;

        expression.setStackLevel(0);

        var srcCalcInfo = new SourceCalculationDataInfo();
        srcCalcInfo.setExpressionID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        srcCalcInfo.setExpressionResultID(UUID.fromString("389d3855-eb30-47ea-a6c8-8bcd2881c0dc"));
        srcCalcInfo.getInterfaces().add(ViewInterface.CONTEXT);
        expression.setSrcCalcInfo(srcCalcInfo);

        var presOptions = new DbgPresentationOptionsOfStringValue();
        presOptions.setMaxTextSize(100);
        presOptions.setStopOnFirstEOL(true);
        expression.setPresOptions(presOptions);

        // when
        var result = httpClient.evalLocalVariables(targetIdLight, List.of(expression), waitTime).get();

        // then
        assertThat(result).isEqualTo(responseResult);
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());
    }

    @Test
    void testEvalExpression() throws ExecutionException, InterruptedException, DebuggerException, IOException {

        //given
        var requestFile = new File("./src/test/resources/httpDebug/RDBGEvalExprRequest.xml");
        var responseFile = new File("./src/test/resources/httpDebug/RDBGEvalExprResponse.xml");

        httpClient.setResponseFile(responseFile);

        var request = httpClient.readRequest(requestFile, RDBGEvalExprRequest.class);
        var response = new RDBGEvalExprResponse();

        var targetIdLight = new DebugTargetIdLight(UUID.fromString("4b695855-1aad-4592-9dd7-0761d7c4f57a"));
        var expression = new CalculationSourceDataStorage();
        var waitTime = 100;

        var srcCalcInfo = new SourceCalculationDataInfo();
        srcCalcInfo.setExpressionID(UUID.fromString("ff869450-1841-45ce-b6cc-d9bcee4e4ffa"));
        srcCalcInfo.setExpressionResultID(UUID.fromString("3eeccc82-daf2-4f3e-9869-502cd9681998"));

        var calcItem = new SourceCalculationDataItem();
        calcItem.setItemType(SourceCalculationDataItemType.EXPRESSION);
        calcItem.setExpression("Max(1,0)");
        srcCalcInfo.getCalcItem().add(calcItem);
        expression.setSrcCalcInfo(srcCalcInfo);

        var presOptions = new DbgPresentationOptionsOfStringValue();
        presOptions.setMaxTextSize(10240);
        expression.setPresOptions(presOptions);

        var calculatedResult = new CalculationResultBaseData();
        calculatedResult.setEvalResultState(CalculationResultState.CORRECTLY);
        calculatedResult.setExpressionResultID(srcCalcInfo.getExpressionResultID());

        var resultValueInfo = new BaseValueInfoData();
        resultValueInfo.setTypeCode(DebugValueTypeCode.NUMBER.getTypeCode());
        resultValueInfo.setTypeName(DebugValueTypeCode.NUMBER.getTypeNameRU());
        resultValueInfo.setValueDecimal(1);
        calculatedResult.setResultValueInfo(resultValueInfo);

        response.getResult().add(calculatedResult);

        // when
        var result = httpClient.evalExpression(targetIdLight, List.of(expression), waitTime).get();

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(calculatedResult);
        assertThat(request).isEqualTo(httpClient.getRequest());
        assertThat(response).isEqualTo(httpClient.getResponse());

    }
}