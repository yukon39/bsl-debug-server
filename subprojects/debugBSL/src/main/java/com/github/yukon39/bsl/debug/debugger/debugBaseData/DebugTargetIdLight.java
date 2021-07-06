package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Облегченный идентификатор предмета отладки
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class DebugTargetIdLight {

    /**
     * Уникальный идентификатор предмета отладки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData", name = "id", required = true)
    private UUID id;
}
