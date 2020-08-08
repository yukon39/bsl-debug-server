package com.github.yukon39.bsl.debugserver.context.managers;

import com.github._1c_syntax.mdclasses.metadata.Configuration;
import com.github.yukon39.bsl.debugserver.context.ModulePropertyId;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleType;
import org.eclipse.lsp4j.debug.Source;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

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

        var modulesByType = configuration.getModulesByType();

        configuration.getModulesByObject()
                .forEach((uri, mdObject) ->
                {
                    var moduleType = modulesByType.get(uri);
                    @Nullable var propertyId = ModulePropertyId.getPropertyId(mdObject, moduleType);

                    var moduleId = new BSLModuleIdInternal();
                    moduleId.setType(BSLModuleType.CONFIG_MODULE);
                    moduleId.setObjectID(UUID.fromString(mdObject.getUuid()));
                    moduleId.setPropertyID(propertyId);

                    var source = addSource(Path.of(uri));
                    source.setAdapterData(moduleId);
                });
    }

    public Source getSource(String path) {

        var srcPath = normalizePath(configuration.getRootPath(), Path.of(path));

        return list.stream()
                .filter(source -> source.getPath().equals(srcPath.toString()))
                .findFirst()
                .orElseGet(() -> addSource(srcPath));
    }

    public Source getSource(BSLModuleIdInternal moduleId) {

        return list.stream()
                .filter(source -> {
                    var m = (BSLModuleIdInternal) source.getAdapterData();
                    return moduleId.isEqualModuleId(m);
                })
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
