package com.github.yukon39.bsl.debug.debugger.debugDBGUICommands;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Описание события о необходимости показа объекта метаданных
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DBGUIExtCmdShowMetadataObject extends DBGUIExtCmdInfoBase {
    public DBGUIExtCmdShowMetadataObject() {
        super(DBGUIExtCmds.SHOW_METADATA_OBJECT);
    }

    /**
     * Полное имя объекта метаданных
     */
    private String metadataObjectName;
}
