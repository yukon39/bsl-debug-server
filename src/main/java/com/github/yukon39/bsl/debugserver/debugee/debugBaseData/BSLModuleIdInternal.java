package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Data
@NoArgsConstructor
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
    private UUID objectID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @NotNull
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private UUID propertyID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private Integer extId;

    @Nullable
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
    private String version;
}
