package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import com.github.yukon39.bsl.debugserver.context.ModulePropertyId;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class BSLModuleIdInternal {

    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private BSLModuleType type;

    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData", name = "URL")
    private String url;

    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String extensionName;

    @NotNull
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID objectID;

    @NotNull
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID propertyID;

    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer extId;

    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String version;

    public BSLModuleIdInternal() {
        objectID = UUID.fromString("00000000-0000-0000-0000-000000000000");
        propertyID = ModulePropertyId.UNKNOWN.getId();
    }

    public boolean isEqualModuleId(@Nullable BSLModuleIdInternal moduleId) {

        if (this == moduleId) {
            return true;
        }

        if (Objects.isNull(moduleId)) {
            return false;
        }

        return objectID.equals(moduleId.objectID) && propertyID.equals(moduleId.propertyID);
    }
}
