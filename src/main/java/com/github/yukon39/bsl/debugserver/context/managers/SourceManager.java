package com.github.yukon39.bsl.debugserver.context.managers;

import com.github._1c_syntax.mdclasses.mdo.MDObjectBase;
import com.github._1c_syntax.mdclasses.metadata.Configuration;
import com.github._1c_syntax.mdclasses.metadata.additional.MDOType;
import com.github._1c_syntax.mdclasses.metadata.additional.ModuleType;
import com.github.yukon39.bsl.debug.data.ModulePropertyId;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleIdLight;
import com.github.yukon39.bsl.debug.debugger.debugBaseData.BSLModuleType;
import com.github.yukon39.bsl.debugserver.context.data.SourceContext;
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
    private Path rootPath;

    public void setConfigurationSource(Path rootPath) {

        this.rootPath = rootPath;
        this.configuration = Configuration.create(rootPath);
        configuration.getModulesByObject().forEach(this::addSourceContext);
    }

    public SourceContext getSourceContext(String path) {

        var srcPath = normalizePath(Path.of(path));

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
        var srcPath = normalizePath(sourcePath);
        var source = createSource(srcPath);

        var moduleType = configuration.getModuleType(uri);
        var propertyId = getPropertyId(mdObject, moduleType);

        var moduleId = new BSLModuleIdInternal();
        moduleId.setType(BSLModuleType.CONFIG_MODULE);
        moduleId.setObjectID(UUID.fromString(mdObject.getUuid()));
        moduleId.setPropertyID(propertyId.getId());
        source.setAdapterData(moduleId);

        var sourceContext = new SourceContext(moduleId, source);
        var moduleIdLight = BSLModuleIdLight.of(moduleId);

        updateLock.lock();
        try {
            sources.put(srcPath, sourceContext);
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

    private Path normalizePath(Path path) {

        if (path.isAbsolute()) {
            var relPath = rootPath.relativize(path);
            return Path.of(rootPath.toString(), relPath.toString());
        } else {
            return Path.of(rootPath.toString(), path.toString());
        }
    }

    private static ModulePropertyId getPropertyId(MDObjectBase metadataObject, ModuleType moduleType) {
        var mdoType = metadataObject.getType();

        switch (moduleType) {

            case OrdinaryApplicationModule:
                return ModulePropertyId.ORDINARY_APPLICATION_MODULE;

            case ManagedApplicationModule:
                return ModulePropertyId.MANAGED_APPLICATION_MODULE;

            case ExternalConnectionModule:
                return ModulePropertyId.EXTERNAL_CONNECTION_MODULE;

            case SessionModule:
                return ModulePropertyId.SESSION_MODULE;

            case CommonModule:
            case WEBServiceModule:
            case HTTPServiceModule:
                return ModulePropertyId.COMMON_MODULE;

            case ValueManagerModule:
                return ModulePropertyId.VALUE_MANAGER_MODULE;

            case ManagerModule:

                if (mdoType == MDOType.SETTINGS_STORAGE) {
                    return ModulePropertyId.SETTINGS_STORE_MANAGER_MODULE;
                } else {
                    return ModulePropertyId.MANAGER_MODULE;
                }

            case ObjectModule:
                return ModulePropertyId.OBJECT_MODULE;

            case RecordSetModule:
                return ModulePropertyId.RECORDSET_MODULE;

            case FormModule:
                return ModulePropertyId.FORM_MODULE;

            case CommandModule:
                return ModulePropertyId.COMMAND_MODULE;

            default:
                return ModulePropertyId.UNKNOWN;
        }
    }
}
