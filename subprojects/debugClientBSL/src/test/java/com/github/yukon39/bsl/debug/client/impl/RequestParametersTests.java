package com.github.yukon39.bsl.debug.client.impl;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestParametersTests {

    @Test
    public void TestURLWithCommand() throws MalformedURLException {
        // Given
        var rootUrl = new URL("http://localhost:1550");
        var parameters = new RequestParameters("testCommand");

        // When
        var requestUrl = parameters.requestURI(rootUrl);

        // Then
        var expectedUrl = "http://localhost:1550/e1crdbg/rdbg?cmd=testCommand";
        assertThat(requestUrl.toString()).isEqualTo(expectedUrl);
    }

    @Test
    public void TestURLWithResource() throws MalformedURLException {
        // Given
        var rootUrl = new URL("http://localhost:1550");
        var parameters = new RequestParameters("testCommand", "testRsc");

        // When
        var requestUrl = parameters.requestURI(rootUrl);

        // Then
        var expectedUrl = "http://localhost:1550/e1crdbg/testRsc?cmd=testCommand";
        assertThat(requestUrl.toString()).isEqualTo(expectedUrl);
    }

    @Test
    public void TestURLWithDebugID() throws MalformedURLException {

        // Given
        var rootUrl = new URL("http://localhost:1550");
        var debugId = UUID.randomUUID();

        var parameters = new RequestParameters("testCommand");
        parameters.setDebugID(debugId);

        // When
        var requestUrl = parameters.requestURI(rootUrl);

        // Then
        var expectedUrl = String.format("http://localhost:1550/e1crdbg/rdbg?dbgui=%s&cmd=testCommand", debugId);
        assertThat(requestUrl.toString()).isEqualTo(expectedUrl);
    }
}
