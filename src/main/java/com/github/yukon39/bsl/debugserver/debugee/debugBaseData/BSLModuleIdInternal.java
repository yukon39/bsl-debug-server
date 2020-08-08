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

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BSLModuleIdInternal)) {
            return false;
        }

        var moduleId = (BSLModuleIdInternal) obj;

        if (this.objectID.equals(moduleId.objectID) && this.propertyID.equals(moduleId.propertyID)) {
            if (Objects.isNull(this.type) || Objects.isNull(moduleId.type)) {
                return true;

            } else if (this.type.equals(moduleId.type)) {

                if (this.type == BSLModuleType.EXTENSION_MODULE) {
                    return Objects.nonNull(this.extensionName) && this.extensionName.equals(moduleId.extensionName);
                } else if (this.type == BSLModuleType.EXT_MD_MODULE) {
                    return Objects.nonNull(this.url) && this.url.equals(moduleId.url);
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        final int INITIAL_PRIME = 43;
        final int MULTIPLIER_PRIME = 59;

        int result = INITIAL_PRIME + super.hashCode();
        result = result * MULTIPLIER_PRIME + objectID.hashCode();
        result = result * MULTIPLIER_PRIME + propertyID.hashCode();
        result = result * MULTIPLIER_PRIME + (Objects.isNull(type) ? INITIAL_PRIME : type.hashCode());
        result = result * MULTIPLIER_PRIME + (Objects.isNull(extensionName) ? INITIAL_PRIME : extensionName.hashCode());
        result = result * MULTIPLIER_PRIME + (Objects.isNull(url) ? INITIAL_PRIME : url.hashCode());

        return result;
    }
}
