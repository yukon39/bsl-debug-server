package com.github.yukon39.bsl.debug.debugger.debugBaseData;

import lombok.Data;

/**
 * Внутненний идентификатор модуля в виде строки
 */
@Data
public class BSLModuleIdInternalStr {

    /**
     * Внутненний идентификатор модуля в виде строки (base64Binary)
     */
    private char [] value = new char[] {};
}
