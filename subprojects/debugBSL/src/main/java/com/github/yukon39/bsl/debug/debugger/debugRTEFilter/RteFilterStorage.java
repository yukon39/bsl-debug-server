package com.github.yukon39.bsl.debug.debugger.debugRTEFilter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Информация об останове по рантайм ошибкам
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class RteFilterStorage {

    /**
     * Останавливаться по ошибке
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private Boolean stopOnErrors = false;

    /**
     * Анализировать текст ошибки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private Boolean analyzeErrorStr = false;

    /**
     * Список отборов по тексту ошибки
     */
    @XmlElement(namespace = "http://v8.1c.ru/8.3/debugger/debugRTEFilter")
    private final List<RteFilterItem> strTemplate = new ArrayList<>();
}
