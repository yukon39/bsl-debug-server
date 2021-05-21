package com.github.yukon39.bsl.debugserver.debugee;

import com.github.yukon39.bsl.debug.client.impl.HttpDebugClient;

public class DebugeeMock extends Debugee {

    public void setAttached(Boolean attached) {
        this.attached = attached;
    }

    public void setHttpClient(HttpDebugClient httpClient) {
        this.httpDebugClient = httpClient;
    }
}
