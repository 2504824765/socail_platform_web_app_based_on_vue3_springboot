package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.config.RedisUtil;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 清除所有缓存
     */
    @DeleteMapping("/clear-all")
    public ResponseMessage<String> clearAllCache() {
        try {
            cacheManager.getCacheNames().forEach(cacheName -> {
                cacheManager.getCache(cacheName).clear();
            });
            return new ResponseMessage<>(200, "所有缓存已清除", "success");
        } catch (Exception e) {
            return new ResponseMessage<>(500, "清除缓存失败: " + e.getMessage(), "error");
        }
    }

    /**
     * 清除指定缓存
     */
    @DeleteMapping("/clear/{cacheName}")
    public ResponseMessage<String> clearCache(@PathVariable String cacheName) {
        try {
            var cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                cache.clear();
                return new ResponseMessage<>(200, "缓存 " + cacheName + " 已清除", "success");
            } else {
                return new ResponseMessage<>(404, "缓存 " + cacheName + " 不存在", "error");
            }
        } catch (Exception e) {
            return new ResponseMessage<>(500, "清除缓存失败: " + e.getMessage(), "error");
        }
    }

    /**
     * 获取缓存统计信息
     */
    @GetMapping("/stats")
    public ResponseMessage<Map<String, Object>> getCacheStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            stats.put("cacheNames", cacheManager.getCacheNames());
            stats.put("cacheCount", cacheManager.getCacheNames().size());
            
            // 获取Redis统计信息
            Map<String, Object> redisStats = new HashMap<>();
            redisStats.put("totalKeys", redisUtil.get("redis:stats:totalKeys"));
            stats.put("redisStats", redisStats);
            
            return new ResponseMessage<Map<String, Object>>(200, "获取缓存统计信息成功", stats);
        } catch (Exception e) {
            return new ResponseMessage<Map<String, Object>>(500, "获取缓存统计信息失败: " + e.getMessage(), null);
        }
    }

    /**
     * 设置缓存过期时间
     */
    @PostMapping("/expire/{key}")
    public ResponseMessage<String> setExpire(@PathVariable String key, @RequestParam long seconds) {
        try {
            boolean result = redisUtil.expire(key, seconds);
            if (result) {
                return new ResponseMessage<>(200, "设置过期时间成功", "success");
            } else {
                return new ResponseMessage<>(500, "设置过期时间失败", "error");
            }
        } catch (Exception e) {
            return new ResponseMessage<>(500, "设置过期时间失败: " + e.getMessage(), "error");
        }
    }

    /**
     * 获取键的过期时间
     */
    @GetMapping("/expire/{key}")
    public ResponseMessage<Long> getExpire(@PathVariable String key) {
        try {
            long expireTime = redisUtil.getExpire(key);
            return new ResponseMessage<>(200, "获取过期时间成功", expireTime);
        } catch (Exception e) {
            return new ResponseMessage<>(500, "获取过期时间失败: " + e.getMessage(), -1L);
        }
    }

    /**
     * 检查键是否存在
     */
    @GetMapping("/exists/{key}")
    public ResponseMessage<Boolean> keyExists(@PathVariable String key) {
        try {
            boolean exists = redisUtil.hasKey(key);
            return new ResponseMessage<>(200, "检查键存在性成功", exists);
        } catch (Exception e) {
            return new ResponseMessage<>(500, "检查键存在性失败: " + e.getMessage(), false);
        }
    }

    /**
     * 删除指定键
     */
    @DeleteMapping("/key/{key}")
    public ResponseMessage<String> deleteKey(@PathVariable String key) {
        try {
            redisUtil.del(key);
            return new ResponseMessage<>(200, "删除键成功", "success");
        } catch (Exception e) {
            return new ResponseMessage<>(500, "删除键失败: " + e.getMessage(), "error");
        }
    }

    /**
     * 获取键的值
     */
    @GetMapping("/value/{key}")
    public ResponseMessage<Object> getValue(@PathVariable String key) {
        try {
            Object value = redisUtil.get(key);
            return new ResponseMessage<>(200, "获取值成功", value);
        } catch (Exception e) {
            return new ResponseMessage<>(500, "获取值失败: " + e.getMessage(), null);
        }
    }

    /**
     * 设置键值对
     */
    @PostMapping("/value/{key}")
    public ResponseMessage<String> setValue(@PathVariable String key, @RequestBody Object value) {
        try {
            boolean result = redisUtil.set(key, value);
            if (result) {
                return new ResponseMessage<>(200, "设置值成功", "success");
            } else {
                return new ResponseMessage<>(500, "设置值失败", "error");
            }
        } catch (Exception e) {
            return new ResponseMessage<>(500, "设置值失败: " + e.getMessage(), "error");
        }
    }

    /**
     * 设置键值对并指定过期时间
     */
    @PostMapping("/value/{key}/expire")
    public ResponseMessage<String> setValueWithExpire(@PathVariable String key, 
                                                    @RequestBody Object value, 
                                                    @RequestParam long seconds) {
        try {
            boolean result = redisUtil.set(key, value, seconds);
            if (result) {
                return new ResponseMessage<>(200, "设置值成功", "success");
            } else {
                return new ResponseMessage<>(500, "设置值失败", "error");
            }
        } catch (Exception e) {
            return new ResponseMessage<>(500, "设置值失败: " + e.getMessage(), "error");
        }
    }
} 