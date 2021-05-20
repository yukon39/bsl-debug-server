package com.github.yukon39.bsl.debugserver.httpDebug;

import com.github.yukon39.bsl.debug.DebuggerException;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.IRDBGRequest;
import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.IRDBGResponse;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class HTTPDebugClientMock extends HTTPDebugClient {

    private final Queue<File> responseQueue = new LinkedList<>();

    public void setResponseFile(File responseFile) {
        responseQueue.add(responseFile);
    }

    public void setResponseFile(String pathname) {
        responseQueue.add(new File(pathname));
    }

    public void setResponseFile() {
        responseQueue.add(null);
    }


    private @Nullable File getResponseFile() {
        return responseQueue.poll();
    }

    @Getter
    private IRDBGRequest request;

    @Getter
    private IRDBGResponse response;

    void setInfobaseAlias(String infobaseAlias) {
        this.infobaseAlias = infobaseAlias;
    }

    void setDebugSession(UUID debugSession) {
        this.debugSession = debugSession;
    }

    <T> T readRequest(File requestFile, Class<T> requestType) throws IOException, DebuggerException {
        var xml = Files.readAllBytes(requestFile.toPath());
        return serializer.deserialize(xml, requestType);
    }

    @Override
    <T> CompletableFuture<T> execute(IRDBGRequest command, Class<T> responseType, RequestParameters requestParameters) {

        request = command;

        return CompletableFuture.completedFuture(null)
                .thenApply(responseBody -> {
                    try {
                        var responseFile = getResponseFile();
                        if (Objects.isNull(responseFile)) {
                            return responseType.getDeclaredConstructor().newInstance();
                        } else {
                            var responseXml = Files.readAllBytes(responseFile.toPath());
                            return serializer.deserialize(responseXml, responseType);
                        }
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                })
                .handle((aResponse, throwable) -> {
                    if (Objects.isNull(throwable)) {
                        response = (IRDBGResponse) aResponse;
                        return aResponse;
                    } else {
                        var httpDebugException = new HTTPDebugException(throwable);
                        throw new CompletionException(httpDebugException);
                    }
                });
    }
}
