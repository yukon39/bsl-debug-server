package com.github.yukon39.bsl.debugserver.debugee.debugForegroundData;

import lombok.Data;

@Data
public class ForegroundWindowData {
    private String computerName;
    private String hwndForeground;
    private Boolean managedWindow;
}
