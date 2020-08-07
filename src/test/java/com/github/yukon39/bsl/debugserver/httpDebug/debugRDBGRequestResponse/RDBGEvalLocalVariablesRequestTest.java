package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.CalculationSourceDataStorage;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.DbgPresentationOptionsOfStringValue;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.SourceCalculationDataInfo;
import com.github.yukon39.bsl.debugserver.debugee.debugCalculations.ViewInterface;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugSerializer;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RDBGEvalLocalVariablesRequestTest {

    @Test
    void testDeserializeFromFile() throws IOException, JAXBException {

        // given
        var request = new RDBGEvalLocalVariablesRequest();
        request.setInfoBaseAlias("DefAlias");
        request.setIdOfDebuggerUI(UUID.fromString("4fb00e47-aaba-49e3-9160-c6c3db20671e"));
        request.setCalcWaitingTime(100);

        var targetId = new DebugTargetIdLight();
        targetId.setId(UUID.fromString("3f80453e-0fa5-4ebd-9400-09bf96d0939f"));
        request.setTargetID(targetId);

        var expr = new CalculationSourceDataStorage();
        expr.setStackLevel(0);

        var srcCalcInfo = new SourceCalculationDataInfo();
        srcCalcInfo.setExpressionID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        srcCalcInfo.setExpressionResultID(UUID.fromString("389d3855-eb30-47ea-a6c8-8bcd2881c0dc"));
        srcCalcInfo.getInterfaces().add(ViewInterface.CONTEXT);
        expr.setSrcCalcInfo(srcCalcInfo);

        var presOptions = new DbgPresentationOptionsOfStringValue();
        presOptions.setMaxTextSize(100);
        presOptions.setStopOnFirstEOL(true);

        expr.setPresOptions(presOptions);

        request.getExpr().add(expr);

        var file = new File("./src/test/resources/httpDebug/RDBGEvalLocalVariablesRequestTest.xml");

        // when
        var xml = Files.readAllBytes(file.toPath());
        var serializer = new HTTPDebugSerializer();
        var response = serializer.deserialize(xml, RDBGEvalLocalVariablesRequest.class);

        // then
        assertThat(request).isEqualTo(response);
    }
}