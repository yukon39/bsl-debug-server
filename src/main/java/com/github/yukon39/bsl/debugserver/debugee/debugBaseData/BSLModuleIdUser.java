package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class BSLModuleIdUser {

    @XmlElement
    private BSLModuleType type;

    @XmlElement(name = "URL")
    private String url;

    @XmlElement(name = "URL")
    private String extensionName;

    @XmlElement(name = "MDObject")
    private String mdObject;

    @XmlElement(name = "MDProperty")
    private String mdProperty;

    @XmlElement(name = "ExtId")
    private Integer extId;

    @XmlElement(name = "Version")
    private String version;
}
