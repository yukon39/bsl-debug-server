package com.github.yukon39.bsl.debugserver.context.managers;

import com.github._1c_syntax.mdclasses.mdo.MDObjectBase;
import com.github._1c_syntax.mdclasses.metadata.Configuration;
import com.github.yukon39.bsl.debugserver.context.ModulePropertyId;
import com.github.yukon39.bsl.debugserver.context.data.SourceContext;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdLight;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleType;
import org.eclipse.lsp4j.debug.Source;

import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class SourceManager {

    private final Map<Path, SourceContext> sources = new HashMap<>();
    private final Map<BSLModuleIdLight, SourceContext> moduleIds = new HashMap<>();
    private final ReentrantLock updateLock = new ReentrantLock();

    private Configuration configuration;

    public void setConfigurationSource(Path rootPath) {

        this.configuration = Configuration.create(rootPath);
        configuration.getModulesByObject().forEach(this::addSourceContext);
    }

    public SourceContext getSourceContext(String path) {

        var rootPath = configuration.getRootPath();
        if (rootPath.isEmpty()) {
            throw new IllegalArgumentException("Invalid configuration path");
        }
        var srcPath = normalizePath(rootPath.get(), Path.of(path));

        var sourceContext = sources.get(srcPath);

        if (Objects.isNull(sourceContext)) {
            throw new IllegalArgumentException("Unknown path=" + path);
        }

        return sourceContext;
    }

    public SourceContext getSourceContext(Source source) {
        return getSourceContext(source.getPath());
    }

    public SourceContext getSourceContext(BSLModuleIdInternal moduleId) {

        var moduleIdLight = BSLModuleIdLight.of(moduleId);

        var sourceContext = moduleIds.get(moduleIdLight);

        if (Objects.isNull(sourceContext)) {
            throw new IllegalArgumentException("Unknown moduleId=" + moduleIdLight.toString());
        }

        return sourceContext;
    }

    private void addSourceContext(URI uri, MDObjectBase mdObject) {

        var sourcePath = Path.of(uri);
        var source = createSource(Path.of(uri));

        var moduleType = configuration.getModuleType(uri);
        var propertyId = ModulePropertyId.getPropertyId(mdObject, moduleType);

        var moduleId = new BSLModuleIdInternal();
        moduleId.setType(BSLModuleType.CONFIG_MODULE);
        moduleId.setObjectID(UUID.fromString(mdObject.getUuid()));
        moduleId.setPropertyID(propertyId.getId());
        source.setAdapterData(moduleId);

        var sourceContext = new SourceContext(moduleId, source);
        var moduleIdLight = BSLModuleIdLight.of(moduleId);

        updateLock.lock();
        try {
            sources.put(sourcePath, sourceContext);
            moduleIds.put(moduleIdLight, sourceContext);
        } finally {
            updateLock.unlock();
        }
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
