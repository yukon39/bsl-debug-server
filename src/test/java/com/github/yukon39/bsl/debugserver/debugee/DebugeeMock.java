package com.github.yukon39.bsl.debugserver.debugee;

import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugClient;
import com.github.yukon39.bsl.debugserver.httpDebug.HTTPDebugClientMock;

public class DebugeeMock extends Debugee {

    public void setAttached(Boolean attached) {
        this.attached = attached;
    }

    public void setHttpClient(HTTPDebugClient httpClient) {
        this.httpDebugClient = httpClient;
    }
}
