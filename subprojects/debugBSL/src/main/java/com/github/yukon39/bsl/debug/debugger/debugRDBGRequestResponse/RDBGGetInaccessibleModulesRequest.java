package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос для получения списка модулей, недоступных для отладки
 */
@Data
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGGetInaccessibleModulesRequest extends RDbgBaseRequest implements IRDBGRequest {
}
