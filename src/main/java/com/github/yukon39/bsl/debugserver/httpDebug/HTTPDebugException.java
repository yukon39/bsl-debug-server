package com.github.yukon39.bsl.debugserver.httpDebug;

public class HTTPDebugException extends Exception {

    public HTTPDebugException(String message) {
        super(message);
    }

    public HTTPDebugException(String message, Throwable cause) {
        super(message, cause);
    }

    public HTTPDebugException(Throwable cause) {
        super(cause);
    }

}
