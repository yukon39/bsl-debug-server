package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DbgTargetStateInfo;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetIdLight;
import com.github.yukon39.bsl.debugserver.debugee.debugDBGUICommands.DBGUIExtCmdInfoStarted;
import com.google.common.eventbus.Subscribe;
import org.eclipse.lsp4j.debug.Thread;
import org.eclipse.lsp4j.debug.ThreadEventArgumentsReason;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ThreadsManager {

    private final ServerContext serverContext;
    private final Map<UUID, Thread> threads = new HashMap<>();
    private Integer counter = 0;

    public ThreadsManager(ServerContext serverContext) {
        this.serverContext = serverContext;
    }

    public @NotNull Thread addDebugTarget(DebugTargetId debugTargetId) {

        var id = debugTargetId.getId();
        synchronized (threads) {
            if (threads.containsKey(id)) {
                return threads.get(id);

            } else {
                var thread = createThread(debugTargetId);
                threads.put(id, thread);
                return thread;
            }
        }
    }

    public @NotNull Thread removeDebugTarget(DebugTargetId debugTargetId) {

        var id = debugTargetId.getId();
        synchronized (threads) {
            if (threads.containsKey(id)) {
                var thread = threads.get(id);
                threads.remove(id);
                return thread;
            } else {
                return createThread(debugTargetId);
            }
        }
    }

    public Thread getThread(DebugTargetId debugTargetId) {
        return threads.get(debugTargetId.getId());
    }

    public DebugTargetIdLight getDebugTargetId(Integer threadId) {

        for (var entry : threads.entrySet()) {
            if (entry.getValue().getId() == threadId) {
                var id = new DebugTargetIdLight();
                id.setId(entry.getKey());
                return id;
            }
        }
        return null;
    }

    public void synchronizeDebugTargetStates(List<DbgTargetStateInfo> debugTargets) {

        synchronized (threads) {
            var copy = new HashMap<UUID, Thread>(threads);
            threads.clear();
            debugTargets.forEach(dbgTargetStateInfo -> {

                var debugTargetId = dbgTargetStateInfo.getTargetID();
                var id = debugTargetId.getId();
                if (copy.containsKey(id)) {
                    threads.put(id, copy.get(id));
                } else {
                    threads.put(id, createThread(debugTargetId));
                }
            });
        }
    }

    public List<Thread> getThreads() {
        var list = new ArrayList<Thread>();
        threads.forEach((uuid, thread) -> {
            list.add(thread);
        });

        return list;
    }

    private @NotNull Thread createThread(DebugTargetId debugTarget) {

        var threadName = threadName(debugTarget);

        var thread = new Thread();
        thread.setId(counter++);
        thread.setName(threadName);
        return thread;
    }

    private String threadName(DebugTargetId debugTarget) {
        var userName = debugTarget.getUserName();

        // TODO: Localize this
        if (userName != null) {
            return String.format("Seance %d (%s), %s", debugTarget.getSeanceNo(), userName, debugTarget.getTargetType());
        } else {
            return String.format("Seance %d, %s", debugTarget.getSeanceNo(), debugTarget.getTargetType());
        }
    }
}