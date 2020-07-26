package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class BSLModuleIdInternal {

    @XmlElement
    private BSLModuleType type;

    @XmlElement(name = "URL")
    private String url;

    @XmlElement
    private String extensionName;

    @XmlElement
    private UUID objectID;

    @XmlElement
    private UUID propertyID;

    @XmlElement
    private Integer extId;

    @XmlElement
    private String version;
}
