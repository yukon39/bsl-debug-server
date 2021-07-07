package com.github.yukon39.bsl.debug.debugger.debugForegroundData;

import lombok.Data;

/**
 * Описание настроек активизации окна отладчика/предмета отладки
 */
@Data
public class ForegroundWindowData {

    /**
     * Имя компьютера с поднимаемым окном
     */
    private String computerName;

    /**
     * Описатель окна
     */
    private String hwndForeground;

    /**
     * Окно управляемого / не управляемого приложения
     */
    private Boolean managedWindow;
}
