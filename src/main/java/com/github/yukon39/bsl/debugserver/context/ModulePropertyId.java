package com.github.yukon39.bsl.debugserver.context;

import com.github._1c_syntax.mdclasses.mdo.MDObjectBase;
import com.github._1c_syntax.mdclasses.metadata.additional.ModuleType;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public enum ModulePropertyId {

    COMMON_MODULE("d5963243-262e-4398-b4d7-fb16d06484f6"),
    OBJECT_MODULE("a637f77f-3840-441d-a1c3-699c8c5cb7e0"),
    MANAGER_MODULE("d1b64a2c-8078-4982-8190-8f81aefda192"),
    RECORDSET_MODULE("9f36fd70-4bf4-47f6-b235-935f73aab43f"),
    ORDINARY_APPLICATION_MODULE("a78d9ce3-4e0c-48d5-9863-ae7342eedf94"),
    MANAGED_APPLICATION_MODULE("d22e852a-cf8a-4f77-8ccb-3548e7792bea"),
    EXTERNAL_CONNECTION_MODULE("a4a9c1e2-1e54-4c7f-af06-4ca341198fac"),
    SESSION_MODULE("9b7bbbae-9771-46f2-9e4d-2489e0ffc702"),
    VALUE_MANAGER_MODULE("3e58c91f-9aaa-4f42-8999-4baf33907b75"),
    SETTINGS_STORE_MANAGER_MODULE("0c8cad23-bf8c-468e-b49e-12f1927c048b"),
    FORM_MODULE("32e087ab-1491-49b6-aba7-43571b41ac2b"),
    COMMAND_MODULE("078a6af8-d22c-4248-9c33-7e90075a3d2c");

    @Getter
    private final UUID id;

    ModulePropertyId(String id) {
        this.id = UUID.fromString(id);
    }

    public static @Nullable UUID getPropertyId(MDObjectBase metadataObject, ModuleType moduleType) {

        var mdoType = metadataObject.getType();

        switch (moduleType) {

            case OrdinaryApplicationModule:
                return ORDINARY_APPLICATION_MODULE.id;

            case ManagedApplicationModule:
                return MANAGED_APPLICATION_MODULE.id;

            case ExternalConnectionModule:
                return EXTERNAL_CONNECTION_MODULE.id;

            case SessionModule:
                return SESSION_MODULE.id;

            case CommonModule:
            case WEBServiceModule:
            case HTTPServiceModule:
                return COMMON_MODULE.id;

            case ValueManagerModule:
                return VALUE_MANAGER_MODULE.id;

            case ManagerModule:
                switch (mdoType) {
                    case SETTINGS_STORAGE:
                        return SETTINGS_STORE_MANAGER_MODULE.id;
                    default:
                        return MANAGER_MODULE.id;
                }

            case ObjectModule:
                return OBJECT_MODULE.id;

            case RecordSetModule:
                return RECORDSET_MODULE.id;

            case FormModule:
                return FORM_MODULE.id;

            case CommandModule:
                return COMMAND_MODULE.id;

            default:
                return null;
        }
    }

    public static @Nullable ModuleType getModuleType(MDObjectBase metadataObject, UUID propertyId) {

        var modulePropertyId = Arrays.stream(ModulePropertyId.values())
                .filter(value -> value.id.equals(propertyId))
                .findFirst();

        var mdoType = metadataObject.getType();

        if (modulePropertyId.isEmpty()) {
            return null;
        }

        switch (modulePropertyId.get()) {

            case ORDINARY_APPLICATION_MODULE:
                return ModuleType.OrdinaryApplicationModule;

            case MANAGED_APPLICATION_MODULE:
                return ModuleType.ManagedApplicationModule;

            case EXTERNAL_CONNECTION_MODULE:
                return ModuleType.ExternalConnectionModule;

            case SESSION_MODULE:
                return ModuleType.SessionModule;

            case COMMON_MODULE:
                switch (mdoType) {
                    case COMMON_MODULE:
                        return ModuleType.CommonModule;
                    case WEB_SERVICE:
                        return ModuleType.WEBServiceModule;
                    case HTTP_SERVICE:
                        return  ModuleType.HTTPServiceModule;
                    default:
                        return null;
                }

            case VALUE_MANAGER_MODULE:
                return ModuleType.ValueManagerModule;

            case MANAGER_MODULE:
            case SETTINGS_STORE_MANAGER_MODULE:
                return ModuleType.ManagerModule;

            case OBJECT_MODULE:
                return ModuleType.ObjectModule;

            case RECORDSET_MODULE:
                return ModuleType.RecordSetModule;

            case FORM_MODULE:
                return ModuleType.FormModule;

            case COMMAND_MODULE:
                return ModuleType.CommandModule;

            default:
                return null;
        }
    }
}