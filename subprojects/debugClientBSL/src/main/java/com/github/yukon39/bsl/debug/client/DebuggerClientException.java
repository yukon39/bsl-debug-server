package com.github.yukon39.bsl.debug.client;

public class DebuggerClientException extends Exception {

    public DebuggerClientException(String message) {
        super(message);
    }

    public DebuggerClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public DebuggerClientException(Throwable cause) {
        super(cause);
    }

}
