# Redis 缓存使用说明

## 概述

本项目已集成Redis缓存功能，用于提高系统性能，减少数据库访问压力。

## 功能特性

- **自动缓存**: 使用Spring Cache注解自动缓存查询结果
- **缓存失效**: 数据更新时自动清除相关缓存
- **手动管理**: 提供API接口手动管理缓存
- **灵活配置**: 支持自定义缓存过期时间和策略

## 配置说明

### 1. Redis连接配置

在 `application.properties` 中配置Redis连接信息：

```properties
# Redis 配置
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.data.redis.password=
spring.data.redis.database=0
spring.data.redis.timeout=2000ms
spring.data.redis.lettuce.pool.max-active=8
spring.data.redis.lettuce.pool.max-wait=-1ms
spring.data.redis.lettuce.pool.max-idle=8
spring.data.redis.lettuce.pool.min-idle=0

# 缓存配置
spring.cache.type=redis
spring.cache.redis.time-to-live=600000
spring.cache.redis.cache-null-values=true
spring.cache.redis.use-key-prefix=true
spring.cache.redis.key-prefix=cache:
```

### 2. 缓存注解使用

#### @Cacheable - 缓存查询结果

```java
@Cacheable(value = "users", key = "'all'")
public List<User> getAllUsers() {
    return userRepository.findAll();
}

@Cacheable(value = "user", key = "#userId")
public User getUser(Integer userId) {
    return userRepository.findById(userId).orElseThrow(...);
}
```

#### @CacheEvict - 清除缓存

```java
@CacheEvict(value = {"users", "userCount"}, allEntries = true)
public User add(UserCreateDTO userCreateDto) {
    // 添加用户逻辑
}
```

## 缓存策略

### 用户相关缓存
- `users`: 用户列表缓存
- `user`: 单个用户缓存
- `userCount`: 用户数量缓存

### 产品相关缓存
- `products`: 产品列表缓存
- `product`: 单个产品缓存
- `productCount`: 产品数量缓存

### 分类相关缓存
- `categories`: 分类列表缓存
- `category`: 单个分类缓存

## 缓存管理API

### 1. 清除所有缓存
```
DELETE /api/cache/clear-all
```

### 2. 清除指定缓存
```
DELETE /api/cache/clear/{cacheName}
```

### 3. 获取缓存统计信息
```
GET /api/cache/stats
```

### 4. 设置缓存过期时间
```
POST /api/cache/expire/{key}?seconds=3600
```

### 5. 获取键的过期时间
```
GET /api/cache/expire/{key}
```

### 6. 检查键是否存在
```
GET /api/cache/exists/{key}
```

### 7. 删除指定键
```
DELETE /api/cache/key/{key}
```

### 8. 获取键的值
```
GET /api/cache/value/{key}
```

### 9. 设置键值对
```
POST /api/cache/value/{key}
Content-Type: application/json

"value"
```

### 10. 设置键值对并指定过期时间
```
POST /api/cache/value/{key}/expire?seconds=3600
Content-Type: application/json

"value"
```

## 使用示例

### 1. 查询用户列表（自动缓存）
```java
// 第一次查询会访问数据库
List<User> users = userService.getAllUsers();

// 第二次查询会从缓存获取
List<User> cachedUsers = userService.getAllUsers();
```

### 2. 添加用户（自动清除缓存）
```java
// 添加用户后，相关的用户缓存会自动清除
User newUser = userService.add(userCreateDTO);
```

### 3. 手动管理缓存
```java
@Autowired
private RedisUtil redisUtil;

// 设置缓存
redisUtil.set("custom:key", "value", 3600);

// 获取缓存
Object value = redisUtil.get("custom:key");

// 删除缓存
redisUtil.del("custom:key");
```

## 性能优化建议

1. **合理设置过期时间**: 根据数据更新频率设置合适的缓存过期时间
2. **避免缓存穿透**: 对于不存在的键，可以缓存null值
3. **使用合适的key**: 使用有意义的key便于管理和调试
4. **监控缓存命中率**: 定期检查缓存效果，调整缓存策略

## 注意事项

1. **数据一致性**: 更新数据时要及时清除相关缓存
2. **内存使用**: 注意Redis内存使用情况，避免内存溢出
3. **网络延迟**: Redis连接的网络延迟可能影响性能
4. **序列化问题**: 确保缓存对象正确序列化

## 故障排除

### 1. Redis连接失败
- 检查Redis服务是否启动
- 验证连接配置是否正确
- 检查网络连接

### 2. 缓存不生效
- 确认@EnableCaching注解已启用
- 检查缓存key是否正确
- 验证缓存配置是否正确

### 3. 序列化错误
- 确保缓存对象实现了Serializable接口
- 检查Jackson配置是否正确

## 扩展功能

### 1. 自定义缓存管理器
可以创建自定义的缓存管理器来实现更复杂的缓存策略。

### 2. 缓存预热
在应用启动时预先加载常用数据到缓存中。

### 3. 缓存监控
集成监控工具来监控缓存使用情况和性能指标。 