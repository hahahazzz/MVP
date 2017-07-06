package com.eflashloan.wct.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/6 14:02
 */
public final class ThreadPool {
    private static final ExecutorService executor = new ThreadPoolExecutor(0, Runtime.getRuntime()
            .availableProcessors() * 2, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static void execute(Runnable task) {
        executor.execute(task);
    }

    public static void shutdownNoew() {
        executor.shutdownNow();
    }
}
