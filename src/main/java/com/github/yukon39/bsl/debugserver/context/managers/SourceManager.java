package com.github.yukon39.bsl.debugserver.context.managers;

import com.github._1c_syntax.mdclasses.metadata.Configuration;
import com.github._1c_syntax.mdclasses.metadata.additional.ModuleType;
import com.github.yukon39.bsl.debugserver.context.ModulePropertyId;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleType;
import org.eclipse.lsp4j.debug.Source;
import org.jetbrains.annotations.Contract;

import java.nio.file.Path;
import java.util.*;

public class SourceManager {

    private final List<Source> list = new ArrayList<>();

    private final Map<BSLModuleIdInternal, Source> map = new HashMap<>();

    private Configuration configuration;

    @Contract(value = " -> new", pure = true)
    public static SourceManager create() {
        return new SourceManager();
    }

    synchronized public void setConfigurationSource(Path rootPath) {

        this.configuration = Configuration.create(rootPath);

        configuration.getModulesByObject()
                .forEach((uri, mdObject) ->
                {

                    var source = addSource(Path.of(uri));

                    var moduleType = configuration.getModuleType(uri);
                    if (moduleType == ModuleType.UNKNOWN) {
                        return;
                    }

                    var propertyId = ModulePropertyId.getPropertyId(mdObject, moduleType);
                    if (propertyId == ModulePropertyId.UNKNOWN) {
                        return;
                    }

                    var moduleId = new BSLModuleIdInternal();
                    moduleId.setType(BSLModuleType.CONFIG_MODULE);
                    moduleId.setObjectID(UUID.fromString(mdObject.getUuid()));
                    moduleId.setPropertyID(propertyId.getId());
                    source.setAdapterData(moduleId);
                });
    }

    public Source getSource(String path) {

        var rootPath = configuration.getRootPath();
        if (rootPath.isEmpty()) {
            throw new IllegalArgumentException("Invalid configuration path");
        }

        var srcPath = normalizePath(rootPath.get(), Path.of(path));

        return list.stream()
                .filter(source -> source.getPath().equals(srcPath.toString()))
                .findFirst()
                .orElseGet(() -> addSource(srcPath));
    }

    public Source getSource(BSLModuleIdInternal moduleId) {

        return list.stream()
                .filter(source -> moduleId.equals(source.getAdapterData()))
                .findFirst()
                .orElseGet(Source::new);
    }

    private Source addSource(Path path) {

        var source = createSource(path);
        list.add(source);
        return source;
    }

    private Source createSource(Path path) {

        var source = new Source();
        source.setPath(path.toString());
        source.setName(path.getFileName().toString());

        return source;
    }

    private Path normalizePath(Path rootPath, Path path) {

        if (path.isAbsolute()) {
            var relPath = rootPath.relativize(path);
            return Path.of(rootPath.toString(), relPath.toString());
        } else {
            return Path.of(rootPath.toString(), path.toString());
        }
    }
}
