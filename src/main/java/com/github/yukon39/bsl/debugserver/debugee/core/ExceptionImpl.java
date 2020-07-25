package com.github.yukon39.bsl.debugserver.debugee.core;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlType(name = "Exception")
public class ExceptionImpl extends GenericException {


    private char[] data;
}
