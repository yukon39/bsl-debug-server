package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * Идентификатор предмета отладки в виде строки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DebugTargetIdStr {

    /**
     * Идентификатор предмета отладки в виде строки
     */
    @XmlValue
    private byte [] value = new byte[] {};

    @Override
    public String toString() {
        return new String(value, StandardCharsets.UTF_8);
    }
}
