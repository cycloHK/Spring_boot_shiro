package com.stu.spring_boot_shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

//自定义shiro缓存管理器
public class RedisCacheManager implements CacheManager {
    /**
     *
     * 参数：认证或者是授权缓存的统一名称
     */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        System.out.println("........" + s);
        return new RedisCache<K, V>(s);
    }
}
