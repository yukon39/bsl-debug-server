package com.github.yukon39.bsl.debugserver.debugee.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DebugTargetIdStr {

    @XmlValue
    private byte [] value = new byte[] {};

    @Override
    public String toString() {
        return new String(value, StandardCharsets.UTF_8);
    }
}
