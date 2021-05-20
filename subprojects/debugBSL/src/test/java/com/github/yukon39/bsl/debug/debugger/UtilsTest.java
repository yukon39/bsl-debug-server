package com.github.yukon39.bsl.debug.debugger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UtilsTest {

    public static String xmlString(String path1, String path2, String path3) throws IOException {

        var resourcePath = Paths.get("src", "test", "resources");
        var resourceDirectory = resourcePath.toFile().getAbsolutePath();
        var xmlFile = Paths.get(resourceDirectory, path1, path2, path3);

        return Files.readString(xmlFile);
    }

}
