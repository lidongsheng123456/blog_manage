package com.zjjhy.common.context;

import lombok.extern.slf4j.Slf4j;

/**
 * BaseContext类用于存储和管理每个请求的唯一标识符
 * 它使用ThreadLocal来确保每个线程都有其独立的请求标识符副本，从而避免并发问题
 */
@Slf4j
public class BaseContext {
    // 使用ThreadLocal存储每个线程独有的请求标识符
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        log.info("当前线程本地用户id--->{}", id);
        threadLocal.set(id);
    }

    public static Long getCurrent() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
