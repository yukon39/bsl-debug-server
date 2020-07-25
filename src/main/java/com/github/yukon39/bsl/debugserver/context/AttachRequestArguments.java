package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.configuration.Language;
import com.github.yukon39.bsl.debugserver.utils.Resources;
import lombok.Getter;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Map;

@Getter
public class AttachRequestArguments {

    private final URL debuggerURL;
    private final String infobaseAlias;
    private final char[] password;
    private final String sessionId;

    public AttachRequestArguments(Map<String, Object> args) {

        var stringDebuggerURL = (String) args.get("debuggerURL");

        URL debuggerURL = null;
        try {
            debuggerURL = new URL(stringDebuggerURL);
        } catch (Exception ignored) {
        }
        this.debuggerURL = debuggerURL;

        this.infobaseAlias = (String) args.get("infobaseAlias");
        this.sessionId = (String) args.get("__sessionId");

        var password = (String) args.get("password");

        if (password == null) {
            this.password = new char[]{};
        } else {
            this.password = password.toCharArray();
        }

        //        String[] targetTypesString = (String[]) args.get("targetTypes");
//        TargetType[] targetTypes;
//        if (targetTypesString != null) {
//            targetTypes = TargetType.defaultTargetTypes();
//        } else if (targetTypesString.length == 1) {
//            if (targetTypesString[0].equals("all")) {
//                targetTypes = TargetType.allTargetTypes();
//            } else {
//                targetTypes = new TargetType[]{};
//            }
//        } else {
//            targetTypes = new TargetType[]{};
//        }
    }

    public void validate(Language language) throws InvalidParameterException {

        if (debuggerURL == null) {
            var msg = Resources.getResourceString(language, this.getClass(), "exceptionNoDebuggerURL");
            throw new InvalidParameterException(msg);
        }

        if (infobaseAlias == null) {
            var msg = Resources.getResourceString(language, this.getClass(), "exceptionNoInfobaseAlias");
            throw new InvalidParameterException(msg);
        }
    }
}
