package com.github.yukon39.bsl.debug.debugger.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlType(name = "RDBGSetBreamOnNextStatementRequest")
public class RDBGSetBreakOnNextStatementRequest extends RDbgBaseRequest implements IRDBGRequest {
}
