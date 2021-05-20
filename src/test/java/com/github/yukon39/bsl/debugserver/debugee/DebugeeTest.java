package com.github.yukon39.bsl.debugserver.debugee;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.AttachDebugUIResult;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationResultState;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debug.debugger.debugCalculations.SourceCalculationDataInfo;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.HTTPServerInitialDebugSettingsData;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugClientMock;
import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class DebugeeTest {

    DebugeeMock debugee;
    HTTPDebugClientMock httpDebugClient;
    EventBus eventBus;

    @BeforeEach
    void beforeEach() throws MalformedURLException, ExecutionException, InterruptedException {

        debugee = new DebugeeMock();
        httpDebugClient = new HTTPDebugClientMock();
        debugee.setHttpClient(httpDebugClient);

        debugee.configure(
                new URL("http://localhost:1550"),
                "DefAlias",
                UUID.fromString("dbe7b1e9-9786-4a25-8da8-304684fa2ce3")
        ).get();

        eventBus = new EventBus();
        debugee.setEventBus(eventBus);
    }

    @Test
    void testAttach() throws ExecutionException, InterruptedException {

        // given
        var data = new HTTPServerInitialDebugSettingsData();

        httpDebugClient.setResponseFile("./src/test/resources/httpDebug/RDBGAttachDebugUIResponseTest.xml");

        // when
        var result = debugee.attach(data).get();

        // then
        assertThat(result).isEqualTo(AttachDebugUIResult.REGISTERED);
        assertThat(debugee.isAttached()).isTrue();
    }

    @Test
    void testGetLocalVariables() throws ExecutionException, InterruptedException {

        // given
        debugee.setAttached(true);

        httpDebugClient.setResponseFile();
        httpDebugClient.setResponseFile("./src/test/resources/httpDebug/RDBGPingDebugUIResponseTest.xml");

        var resultId = UUID.fromString("c843e036-8caf-4a3d-85d5-b96225ed17da");
        var targetId = new DebugTargetIdLight(UUID.randomUUID());

        var srcCalcInfo = new SourceCalculationDataInfo();
        srcCalcInfo.setExpressionResultID(resultId);

        var expression = new CalculationSourceDataStorage();
        expression.setSrcCalcInfo(srcCalcInfo);

        // when
        var delayedExecutor = CompletableFuture.delayedExecutor(1L, TimeUnit.SECONDS);
        var delayedStep = CompletableFuture.runAsync(() -> debugee.run(), delayedExecutor);
        var variablesStep = debugee.getLocalVariables(targetId, expression);

        CompletableFuture.allOf(variablesStep, delayedStep).get();

        var result = variablesStep.get();

        // then
        assertThat(result).isNotNull();
        assertThat(result.getExpressionResultID()).isEqualTo(resultId);
        assertThat(result.getEvalResultState()).isEqualTo(CalculationResultState.CORRECTLY);
    }

    @Test
    void testEvaluateExpressions() throws ExecutionException, InterruptedException {

        // given
        debugee.setAttached(true);

        httpDebugClient.setResponseFile();
        httpDebugClient.setResponseFile("./src/test/resources/httpDebug/RDBGPingDebugUIResponseEvalExpr.xml");

        var resultId = UUID.fromString("12219964-cb7a-4866-a6e8-32ad547e2555");
        var targetId = new DebugTargetIdLight(UUID.randomUUID());

        var srcCalcInfo = new SourceCalculationDataInfo();
        srcCalcInfo.setExpressionResultID(resultId);

        var expression = new CalculationSourceDataStorage();
        expression.setSrcCalcInfo(srcCalcInfo);

        // when
        var delayedExecutor = CompletableFuture.delayedExecutor(1L, TimeUnit.SECONDS);
        var delayedStep = CompletableFuture.runAsync(() -> debugee.run(), delayedExecutor);
        var variablesStep = debugee.evaluateExpressions(targetId, List.of(expression));

        CompletableFuture.allOf(variablesStep, delayedStep).get();

        var result = variablesStep.get();

        // then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);

        var calculatedData = result.get(0);
        assertThat(calculatedData.getExpressionResultID()).isEqualTo(resultId);
        assertThat(calculatedData.getEvalResultState()).isEqualTo(CalculationResultState.CORRECTLY);
    }

}
