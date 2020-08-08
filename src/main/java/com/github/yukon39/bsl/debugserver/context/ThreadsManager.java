package com.github.yukon39.bsl.debugserver.context;

import com.github.yukon39.bsl.debugserver.context.data.ThreadContext;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DbgTargetStateInfo;
import com.github.yukon39.bsl.debugserver.debugee.debugBaseData.DebugTargetId;
import org.eclipse.lsp4j.debug.Thread;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ThreadsManager {

    private final Map<Integer, ThreadContext> threads = new HashMap<>();
    private final Map<UUID, ThreadContext> debugTargets = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);
    private final ReentrantLock updateLock = new ReentrantLock();

    public ThreadContext createThreadContext(DebugTargetId debugTargetId) {

        var thread = createThread(debugTargetId);
        var threadContext = new ThreadContext(thread, debugTargetId);

        updateLock.lock();
        try {

            threads.put(thread.getId(), threadContext);
            debugTargets.put(debugTargetId.getId(), threadContext);

        } finally {
            updateLock.unlock();
        }

        return threadContext;
    }

    public ThreadContext getThreadContext(DebugTargetId debugTargetId) {

        var threadContext = debugTargets.get(debugTargetId.getId());

        if (Objects.isNull(threadContext)) {
            throw new IllegalArgumentException("Unknown debugTargetId.Id=" + debugTargetId.getId());
        }

        return threadContext;
    }

    public ThreadContext getThreadContext(Thread thread) {
        return getThreadContext(thread.getId());
    }

    public ThreadContext getThreadContext(Integer threadId) {

        var threadContext = threads.get(threadId);

        if (Objects.isNull(threadContext)) {
            throw new IllegalArgumentException("Unknown threadId=" + threadId);
        }

        return threadContext;
    }

    public ThreadContext removeThreadContext(DebugTargetId debugTargetId) {

        updateLock.lock();
        try {
            var threadContext = debugTargets.remove(debugTargetId.getId());

            if (Objects.isNull(threadContext)) {
                throw new IllegalArgumentException("Unknown debugTargetId.Id=" + debugTargetId.getId());
            }

            threads.remove(threadContext.getThread().getId());

            return threadContext;

        } finally {
            updateLock.unlock();
        }
    }

    public void synchronizeDebugTargetStates(List<DbgTargetStateInfo> debugTargetsStates) {

        updateLock.lock();
        try {

            var copy = new HashMap<>(debugTargets);

            threads.clear();
            debugTargets.clear();

            debugTargetsStates.stream()
                    .map(DbgTargetStateInfo::getTargetID)
                    .forEach(debugTargetId -> {

                        var threadContext = copy.remove(debugTargetId.getId());
                        if (Objects.isNull(threadContext)) {
                            var thread = createThread(debugTargetId);
                            threadContext = new ThreadContext(thread, debugTargetId);
                        }

                        threads.put(threadContext.getThread().getId(), threadContext);
                        debugTargets.put(debugTargetId.getId(), threadContext);
                    });

        } finally {
            updateLock.unlock();
        }
    }

    public List<Thread> getThreads() {

        return threads.values()
                .stream()
                .map(ThreadContext::getThread)
                .collect(Collectors.toList());
    }

    private @NotNull Thread createThread(DebugTargetId debugTarget) {

        var threadName = threadName(debugTarget);

        var thread = new Thread();
        thread.setId(counter.getAndIncrement());
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