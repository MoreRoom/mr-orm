package com.zzz.jff.core.factory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther zhaowei.zhang
 * @since 2018/4/27 19:29
 */
public abstract class AbsSqlSessionFactory {

    /** 需要加载的数据源集合 */
    protected ConcurrentHashMap<String, Object> dataSources = new ConcurrentHashMap<>();

//    protected
}
