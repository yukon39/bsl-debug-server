package com.github.yukon39.bsl.debugserver.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor(onConstructor = @__({@JsonCreator(mode = JsonCreator.Mode.DISABLED)}))
public class DebugServerConfiguration {

    private Language language;
    private String pathFormat;
    private boolean linesStartAt1 = true;
    private boolean columnsStartAt1 = true;
    private boolean supportsVariableType = false;
    private boolean supportsVariablePaging = false;
    private boolean supportsRunInTerminalRequest = false;
    private boolean supportsProgressReporting = false;

    private DebugServerConfiguration() {
        this.language = Language.DEFAULT_LANGUAGE;
        this.pathFormat = "";
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull DebugServerConfiguration create() {
        return new DebugServerConfiguration();
    }
}