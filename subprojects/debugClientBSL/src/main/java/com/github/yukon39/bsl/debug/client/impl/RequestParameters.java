package com.github.yukon39.bsl.debug.client.impl;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.UUID;

public class RequestParameters {

    private final String resource;
    private final Map<String,String> parameters = new HashMap<>();

    public RequestParameters(String command) {
        this(command, "rdbg");
    }

    public RequestParameters(String command, String resource)
    {
        this.resource = resource;
        parameters.put("cmd", command);
    }

    public void setDebugID(@NotNull UUID debugId)
    {
        parameters.put("dbgui", debugId.toString());
    }

    public URI requestURI(URL rootURL) {

        var joiner = new StringJoiner("&");
        parameters.forEach((key, value) -> joiner.add(String.format("%s=%s", key, value)));
        var query = joiner.toString();

        var stringUri = String.format("%s/e1crdbg/%s?%s", rootURL, resource, query);

        return URI.create(stringUri);
    }
}
