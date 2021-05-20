package com.github.yukon39.bsl.debug.data.core;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

import jakarta.xml.bind.annotation.XmlAttribute;
import java.util.UUID;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericException {

    @XmlAttribute
    private UUID clsid;

    @XmlAttribute
    private Boolean encoded;

    private String descr;
    private GenericException inner;
}
