package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class BSLModuleIdInternal {

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private BSLModuleType type;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData", name = "URL")
    private String url;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String extensionName;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID objectID;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID propertyID;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer extId;

    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String version;

    public boolean isEqualModuleId(BSLModuleIdInternal moduleId) {

        if (this == moduleId) {
            return true;
        }

        if (Objects.isNull(moduleId)) {
            return false;
        }

        return (Objects.isNull(objectID) || objectID.equals(moduleId.objectID))
                && (Objects.isNull(propertyID) || propertyID.equals(moduleId.propertyID));
        //return propertyID == moduleId.propertyID;

        //            if (Objects.isNull(type) || Objects.isNull(moduleId.type)) {
        //                return true;
        //
        //            } else if (type != moduleId.type) {
        //                return false;
        //
        //            } else if (type == BSLModuleType.CONFIG_MODULE) {
        //                return true;
        //
        //            } else if (type == BSLModuleType.EXTENSION_MODULE) {
        //
        //                return (Objects.isNull(extensionName) && Objects.isNull(moduleId.extensionName))
        //                        || extensionName.equals(moduleId.extensionName);
        //
        //            } else if (type == BSLModuleType.EXT_MD_MODULE) {
        //
        //                return (Objects.isNull(url) && Objects.isNull(moduleId.url))
        //                        || url.equals(moduleId.url);
        //            }
        //return this.propertyID == moduleId.propertyID && this.objectID == moduleId.objectID;
    }
}
