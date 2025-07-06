package com.cz.springboot_demo;

import com.cz.springboot_demo.config.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisCacheTest {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testRedisConnection() {
        // 测试Redis连接
        String testKey = "test:connection";
        String testValue = "Hello Redis";
        
        // 设置值
        boolean setResult = redisUtil.set(testKey, testValue);
        assertTrue(setResult, "Redis设置值应该成功");
        
        // 获取值
        Object retrievedValue = redisUtil.get(testKey);
        assertEquals(testValue, retrievedValue, "获取的值应该与设置的值相同");
        
        // 检查键是否存在
        boolean exists = redisUtil.hasKey(testKey);
        assertTrue(exists, "键应该存在");
        
        // 删除键
        redisUtil.del(testKey);
        
        // 再次检查键是否存在
        exists = redisUtil.hasKey(testKey);
        assertFalse(exists, "键应该已被删除");
    }

    @Test
    public void testCacheManager() {
        // 测试缓存管理器
        assertNotNull(cacheManager, "缓存管理器不应该为null");
        
        // 获取缓存名称
        var cacheNames = cacheManager.getCacheNames();
        assertNotNull(cacheNames, "缓存名称列表不应该为null");
        
        // 检查是否有缓存配置
        assertTrue(cacheNames.size() > 0, "应该有缓存配置");
    }

    @Test
    public void testCacheExpiration() {
        // 测试缓存过期时间
        String testKey = "test:expiration";
        String testValue = "Expiration Test";
        
        // 设置值，过期时间为2秒
        boolean setResult = redisUtil.set(testKey, testValue, 2);
        assertTrue(setResult, "设置带过期时间的值应该成功");
        
        // 立即获取值
        Object value = redisUtil.get(testKey);
        assertEquals(testValue, value, "立即获取的值应该正确");
        
        // 获取过期时间
        long expireTime = redisUtil.getExpire(testKey);
        assertTrue(expireTime > 0, "过期时间应该大于0");
        
        // 等待3秒后检查键是否过期
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 检查键是否还存在
        boolean exists = redisUtil.hasKey(testKey);
        assertFalse(exists, "键应该已经过期");
    }

    @Test
    public void testHashOperations() {
        // 测试Hash操作
        String hashKey = "test:hash";
        String field = "name";
        String value = "Test User";
        
        // 设置Hash字段
        boolean hsetResult = redisUtil.hset(hashKey, field, value);
        assertTrue(hsetResult, "设置Hash字段应该成功");
        
        // 获取Hash字段
        Object retrievedValue = redisUtil.hget(hashKey, field);
        assertEquals(value, retrievedValue, "获取的Hash字段值应该正确");
        
        // 检查Hash字段是否存在
        boolean hExists = redisUtil.hHasKey(hashKey, field);
        assertTrue(hExists, "Hash字段应该存在");
        
        // 删除Hash字段
        redisUtil.hdel(hashKey, field);
        
        // 再次检查Hash字段是否存在
        hExists = redisUtil.hHasKey(hashKey, field);
        assertFalse(hExists, "Hash字段应该已被删除");
    }

    @Test
    public void testListOperations() {
        // 测试List操作
        String listKey = "test:list";
        String value1 = "item1";
        String value2 = "item2";
        
        // 添加元素到列表
        boolean lsetResult1 = redisUtil.lSet(listKey, value1);
        boolean lsetResult2 = redisUtil.lSet(listKey, value2);
        assertTrue(lsetResult1 && lsetResult2, "添加列表元素应该成功");
        
        // 获取列表长度
        long listSize = redisUtil.lGetListSize(listKey);
        assertEquals(2, listSize, "列表长度应该为2");
        
        // 获取列表元素
        var listElements = redisUtil.lGet(listKey, 0, -1);
        assertNotNull(listElements, "列表元素不应该为null");
        assertEquals(2, listElements.size(), "列表元素数量应该为2");
        assertEquals(value1, listElements.get(0), "第一个元素应该正确");
        assertEquals(value2, listElements.get(1), "第二个元素应该正确");
        
        // 删除列表
        redisUtil.del(listKey);
        
        // 检查列表是否还存在
        boolean exists = redisUtil.hasKey(listKey);
        assertFalse(exists, "列表应该已被删除");
    }
} 