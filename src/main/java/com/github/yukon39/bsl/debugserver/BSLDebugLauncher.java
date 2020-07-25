package com.github.yukon39.bsl.debugserver;

import com.github.yukon39.bsl.debugserver.cli.DebugAdapterStartCommand;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;

@Command(
        name = "bsl-debug-server",
        subcommands = {
                DebugAdapterStartCommand.class
        },
        usageHelpAutoWidth = true
)
public class BSLDebugLauncher implements Callable<Integer> {

    private static final String DEFAULT_COMMAND = "dap";

    public static void main(String[] args) {
        var app = new BSLDebugLauncher();
        var cmd = new CommandLine(app);

        if (args.length == 0) {
            args = addDefaultCommand(args);
        }

        int result = cmd.execute(args);
        if (result >= 0) {
            System.exit(result);
        }
    }

    private static String[] addDefaultCommand(String[] args) {
        List<String> tmpList = new ArrayList<>(Arrays.asList(args));
        tmpList.add(0, DEFAULT_COMMAND);
        args = tmpList.toArray(new String[0]);
        return args;
    }

    public Integer call() {
        // заглушка, командой как таковой не пользуемся
        return 0;
    }
}
