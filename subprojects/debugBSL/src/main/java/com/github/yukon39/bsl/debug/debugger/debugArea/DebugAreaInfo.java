package com.github.yukon39.bsl.debug.debugger.debugArea;

import com.github.yukon39.bsl.debug.debugger.debugBaseData.DebugTargetType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Описание области отладки
 */
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class DebugAreaInfo {

    /**
     * Имя области
     */
    @XmlElement
    private String name;

    /**
     * Признак использования разделителей области данных
     */
    @XmlElement
    private Boolean useMaskOfDSArea;

    /**
     * Список разделителей, описывающих область данных
     */
    @XmlElement
    private final List<DebugAreaDSPairInfo> dsPairInfo = new ArrayList<>();

    /**
     * Признак использования типов предметов отладки
     */
    @XmlElement
    private Boolean useMaskOfTargetTypes;

    /**
     * Список типов предметов отладки
     */
    @XmlElement
    private final List<DebugTargetType> targetType = new ArrayList<>();

    /**
     * Признак использования маски с идентификаторами пользователей
     */
    @XmlElement
    private Boolean useMaskOfUsers;

    /**
     * Список идентификаторов пользователей
     */
    @XmlElement
    private final List<DebugAreaUserInfo> userInfo = new ArrayList<>();
}