package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.configuration.Language;
import com.github.yukon39.bsl.debugserver.utils.Resources;
import lombok.Getter;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Objects;

@Getter
public class AttachRequestArguments {

    private final URL debuggerURL;
    private final String infobaseAlias;
    private final char[] password;
    private final String cwd;
    private final String sourcePath;

    public AttachRequestArguments(Map<String, Object> args) {

        var stringDebuggerURL = (String) args.get("debuggerURL");

        URL localDebuggerURL;
        try {
            localDebuggerURL = new URL(stringDebuggerURL);
        } catch (Exception ignored) {
            localDebuggerURL = null;
        }
        debuggerURL = localDebuggerURL;

        this.infobaseAlias = (String) args.get("infobaseAlias");

        var passwordString = (String) args.get("password");

        if (passwordString == null) {
            password = new char[]{};
        } else {
            password = passwordString.toCharArray();
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

        var args_cwd = (String) args.get("cwd");
        if (Objects.isNull(args_cwd) || args_cwd.isBlank()) {
            cwd = Paths.get("").toAbsolutePath().toString();
        } else {
            cwd = args_cwd;
        }

        Map<String, Object> sources = (Map<String, Object>) args.get("sources");
        var args_sourcePath = "";
        if (!Objects.isNull(sources)) {
            args_sourcePath = (String) sources.get("path");
        }

        if (Objects.isNull(args_sourcePath) || args_sourcePath.isBlank()) {
            args_sourcePath = "src/cf";
        }

        if (Path.of(args_sourcePath).isAbsolute()) {
            sourcePath = args_sourcePath;
        } else {
            sourcePath = Path.of(cwd, args_sourcePath).toString();
        }
    }

    public void validate(Language language) {

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
