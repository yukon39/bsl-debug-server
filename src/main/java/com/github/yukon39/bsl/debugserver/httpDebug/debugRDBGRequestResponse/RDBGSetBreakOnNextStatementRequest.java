package com.github.yukon39.bsl.debugserver.httpDebug.debugRDBGRequestResponse;

import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlType(name = "RDBGSetBreamOnNextStatementRequest")
public class RDBGSetBreakOnNextStatementRequest extends RDbgBaseRequest implements IRDBGRequest {
}
