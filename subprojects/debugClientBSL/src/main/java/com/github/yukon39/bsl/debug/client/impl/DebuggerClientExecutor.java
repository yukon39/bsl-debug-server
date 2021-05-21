package com.github.yukon39.bsl.debug.client.impl;

import com.github.yukon39.bsl.debug.DebuggerXmlSerializer;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.IRDBGRequest;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.IRDBGResponse;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class DebuggerClientExecutor {

    private final HttpClient client;
    private final URL rootURL;

    private DebuggerClientExecutor(URL rootURL, HttpClient client) {
        this.rootURL = rootURL;
        this.client = client;
    }

    @Contract("_ -> new")
    public static @NotNull DebuggerClientExecutor newInstance(URL rootURL) {
        return new DebuggerClientExecutor(rootURL, newHttpClient());
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull DebuggerClientExecutor newInstance(URL rootURL, HttpClient client) {
        return new DebuggerClientExecutor(rootURL, client);
    }

    public <T extends IRDBGResponse> CompletableFuture<T> executeAsync(
            IRDBGRequest request, RequestParameters requestParameters, Class<T> responseType) {

        URI requestURI;
        String requestContent;

        try {
            requestURI = requestParameters.requestURI(rootURL);
            requestContent = DebuggerXmlSerializer.serialize(request);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        return httpResponseContent(requestURI, requestContent)
                .thenCompose(responseContent -> deserializeAsync(responseContent, responseType));
    }

    private @NotNull CompletableFuture<byte[]> httpResponseContent(URI requestURI, String requestContent) {

        var bodyPublisher = HttpRequest.BodyPublishers.ofString(requestContent);

        var httpRequest = HttpRequest.newBuilder(requestURI)
                .header("Accept", "application/xml")
                .header("Content-Type", "application/xml; charset=utf-8")
                .header("Accept-Encoding", "gzip")
                .header("User-Agent", "1CV8")
                .POST(bodyPublisher)
                .build();

        var bodyHandler = HttpResponse.BodyHandlers.ofByteArray();

        return client.sendAsync(httpRequest, bodyHandler)
                .thenApply(HttpResponse::body);
    }

    private <T extends IRDBGResponse> @NotNull CompletableFuture<T> deserializeAsync(byte[] responseContent, Class<T> responseType) {

        try {
            T response;
            if (responseContent.length == 0) {
                response = responseType.getDeclaredConstructor().newInstance();
            } else {
                response = DebuggerXmlSerializer.deserialize(responseContent, responseType);
            }
            return CompletableFuture.completedFuture(response);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    private static HttpClient newHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }
}
