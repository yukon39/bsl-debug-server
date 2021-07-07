package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Запрос с нотификацией о завершении сеансов
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGNotifyOnFinishSeanceRequest extends RDbgBaseRequest {

    /**
     * Идентификатор сеанса
     */
    private final List<UUID> seanceID = new ArrayList<>();
}
