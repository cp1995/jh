package com.cp.dd.common.util.sys;



/**
 * 利用线程本地变量来保存登录信息
 *
 * @author chengp
 * @date 2019/9/25
 */
public class SessionCache {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static <T> void put(T t) {
        threadLocal.set(t);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get() {
        return (T) threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }





}