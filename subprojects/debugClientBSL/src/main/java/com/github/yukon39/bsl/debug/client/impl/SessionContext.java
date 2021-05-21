package com.github.yukon39.bsl.debug.client.impl;

import com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse.RDbgBaseRequest;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.security.InvalidParameterException;
import java.util.UUID;

public class SessionContext {

    private final String infobaseAlias;

    @Getter
    private final UUID debugSession;

    private SessionContext(String infobaseAlias, UUID debugSession) {
        this.infobaseAlias = infobaseAlias;
        this.debugSession = debugSession;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull SessionContext newInstance(String infobaseAlias, UUID debugSession) {
        return new SessionContext(infobaseAlias, debugSession);
    }

    public <T extends RDbgBaseRequest> @NotNull T newSessionRequest(Class<T> requestType)  {

        try {
            var request = requestType.getDeclaredConstructor().newInstance();
            request.setIdOfDebuggerUI(debugSession);
            request.setInfoBaseAlias(infobaseAlias);

            return request;

        } catch (Exception e) {
            throw new InvalidParameterException();
        }
    }
}
