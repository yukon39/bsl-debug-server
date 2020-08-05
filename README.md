# Debug Adapter Protocol implementation for 1С:Enterprise Platform

This project implements [Debug Adapter Protocol](https://microsoft.github.io/debug-adapter-protocol/) for [1C:Enterprise Platform](https://1c-dn.com/) debugging.

Based on [Eclipse LSP4J](https://github.com/eclipse/lsp4j)

Current DAP schema version: 1.37.0 (LSP4J 0.9.0)

## How to use

Run JAR-file with parameter `--dap`:
```sh
java -Dfile.encoding=UTF-8 -jar bsl-debug-server.jar --dap
```
> Use `-Dfile.encoding=UTF-8` to avoid encoding problems

## Provided capabilities

| Capability                 | Supported |
| ---------------------------|:---------:|
| Basic                      | In process |
| ConfigurationDoneRequest   | [х] |
| FunctionBreakpoints        | [ ] |
| ConditionalBreakpoints     | [ ] |
| HitConditionalBreakpoints  | [ ] |
| EvaluateForHovers          | [ ] |
| StepBack                   | [ ] |
| SetVariable                | [ ] |
| RestartFrame               | [ ] |
| GotoTargetsRequest         | [ ] |
| StepInTargetsRequest       | [ ] |
| CompletionsRequest         | [ ] |
| ModulesRequest             | [ ] |
| ChecksumAlgorithms         | [ ] |
| RestartRequest             | [х] |
| ExceptionOptions           | [ ] |
| ValueFormattingOptions     | [ ] |
| ExceptionInfoRequest       | [ ] |
| TerminateDebuggee          | [ ] |
| DelayedStackTraceLoading   | [ ] |
| LoadedSourcesRequest       | [ ] |
| LogPoints                  | [ ] |
| TerminateThreadsRequest    | [ ] |
| SetExpression              | [ ] |
| TerminateRequest           | [ ] |
| DataBreakpoints            | [ ] |
| ReadMemoryRequest          | [ ] |
| DisassembleRequest         | [ ] |
| CancelRequest              | --- |
| BreakpointLocationsRequest | [ ] |

## Supported Features

| Feature                 | Supported |
| ------------------------|:---------:|
| runInTerminal           | [ ] |
| initialize              | [x] |
| configurationDone       | [x] |
| launch                  | [ ] |
| attach                  | [х] |
| restart                 | [х] |
| disconnect              | [х] |
| terminate               | [ ] |
| breakpointLocations     | [ ] |
| setBreakpoints          | [х] |
| setFunctionBreakpoints  | [ ] |
| setExceptionBreakpoints | [ ] |
| dataBreakpointInfo      | [ ] |
| setDataBreakpoints      | [ ] |
| continue                | [х] |
| next                    | [ ] |
| stepIn                  | [х] |
| stepOut                 | [х] |
| stepBack                | [ ] |
| reverseContinue         | [ ] |
| restartFrame            | [ ] |
| goto                    | [ ] |
| pause                   | [х] |
| stackTrace              | [х] |
| scopes                  | [х] |
| variables               | [ ] |
| setVariable             | [ ] |
| source                  | [х] |
| threads                 | [х] |
| terminateThreads        | [ ] |
| modules                 | [ ] |
| loadedSources           | [ ] |
| evaluate                | [ ] |
| setExpression           | [ ] |
| stepInTargets           | [ ] |
| gotoTargets             | [ ] |
| completions             | [ ] |
| exceptionInfo           | [ ] |
| readMemory              | [ ] |
| disassemble             | [ ] |