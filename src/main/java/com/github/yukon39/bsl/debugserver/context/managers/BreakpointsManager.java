package com.github.yukon39.bsl.debugserver.context.managers;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.BSLModuleIdInternal;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.BreakpointInfo;
import com.github.yukon39.bsl.debugserver.debugee.debugBreakpoints.ModuleBPInfoInternal;
import org.eclipse.lsp4j.debug.Breakpoint;
import org.eclipse.lsp4j.debug.Source;
import org.eclipse.lsp4j.debug.SourceBreakpoint;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BreakpointsManager {

    private final Set<Breakpoint> list = new HashSet<>();
    private Integer counter = 0;

    synchronized public void setBreakpoints(Source source, SourceBreakpoint[] sourceBreakpoints) {

        var oldBreakpoints = sourceBreakpoints(source).collect(Collectors.toSet());

        list.removeAll(oldBreakpoints);

        Arrays.stream(sourceBreakpoints).mapToInt(SourceBreakpoint::getLine).forEach(line -> {

            var breakpoint = oldBreakpoints.stream()
                    .filter(b -> b.getLine().equals(line))
                    .findFirst()
                    .orElseGet(() -> {
                                var b = new Breakpoint();
                                b.setId(counter++);
                                b.setLine(line);
                                b.setSource(source);
                                b.setVerified(true);
                                return b;
                            }
                    );
            list.add(breakpoint);
        });
    }

    public List<Breakpoint> getBreakpoints(Source source) {

        var bpList = new ArrayList<Breakpoint>();

        sourceBreakpoints(source).forEach(bpList::add);

        return bpList;

    }

    public List<ModuleBPInfoInternal> getModuleBPInfo() {

        var result = new ArrayList<ModuleBPInfoInternal>();

        var map = new HashMap<String, ModuleBPInfoInternal>();

        list.forEach(breakpoint -> {

            var moduleBPInfo = map.get(breakpoint.getSource().getPath());
            if (Objects.isNull(moduleBPInfo)) {
                moduleBPInfo = new ModuleBPInfoInternal();
                moduleBPInfo.setId((BSLModuleIdInternal) breakpoint.getSource().getAdapterData());
                result.add(moduleBPInfo);
            }

            var breakpointInfo = new BreakpointInfo();
            breakpointInfo.setLine(breakpoint.getLine());
            moduleBPInfo.getBpInfo().add(breakpointInfo);
        });

        return result;
    }

    private Stream<Breakpoint> sourceBreakpoints(Source source) {
        var sourcePath = source.getPath();
        return list.stream()
                .filter(b -> b.getSource().getPath().equals(sourcePath));
    }
}
