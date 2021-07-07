package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Запрос на выполнение останова на следующей строке кода
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlType(name = "RDBGSetBreamOnNextStatementRequest")
@XmlRootElement(name = "request", namespace = "http://v8.1c.ru/8.3/debugger/debugBaseData")
public class RDBGSetBreakOnNextStatementRequest extends RDbgBaseRequest implements IRDBGRequest {
}
