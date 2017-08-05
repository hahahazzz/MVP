package com.dmh.mvp.util;

import org.junit.Test;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/13 14:31
 */
public class ThreadPoolTest {
    @Test
    public void execute() throws Exception {
        ThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("aaa");
            }
        });
    }

    @Test
    public void shutdownNow() throws Exception {
        ThreadPool.shutdownNow();
    }
}