# Redis 缓存快速开始指南

## 🚀 快速启动

### 1. 启动 Redis 服务

#### macOS/Linux:
```bash
# 使用提供的脚本
./start-redis.sh

# 或者手动启动
redis-server
```

#### Windows:
```cmd
# 使用提供的脚本
start-redis.bat

# 或者手动启动
redis-server
```

### 2. 验证 Redis 连接
```bash
redis-cli ping
```
应该返回 `PONG`

### 3. 启动 Spring Boot 应用
```bash
mvn spring-boot:run
```

## 📋 功能验证

### 1. 测试缓存API
```bash
# 获取缓存统计信息
curl http://localhost:8080/api/cache/stats

# 设置测试缓存
curl -X POST http://localhost:8080/api/cache/value/test-key \
  -H "Content-Type: application/json" \
  -d "\"test-value\""

# 获取缓存值
curl http://localhost:8080/api/cache/value/test-key
```

### 2. 测试业务缓存
```bash
# 获取所有用户（会被缓存）
curl http://localhost:8080/api/users

# 获取所有产品（会被缓存）
curl http://localhost:8080/api/products

# 获取所有分类（会被缓存）
curl http://localhost:8080/api/categories
```

## 🔧 配置说明

### Redis 连接配置
- **主机**: localhost
- **端口**: 6379
- **密码**: 无
- **数据库**: 0
- **连接池**: 最大8个连接

### 缓存配置
- **默认过期时间**: 10分钟
- **缓存空值**: 是
- **使用键前缀**: 是
- **键前缀**: cache:

## 📊 缓存策略

### 用户缓存
- `users`: 用户列表
- `user`: 单个用户
- `userCount`: 用户数量

### 产品缓存
- `products`: 产品列表
- `product`: 单个产品
- `productCount`: 产品数量

### 分类缓存
- `categories`: 分类列表
- `category`: 单个分类

### 订单缓存
- `orders`: 订单列表
- `order`: 单个订单
- `orderCount`: 订单数量

### 配送缓存
- `deliveries`: 配送列表
- `delivery`: 单个配送
- `deliveryCount`: 配送数量

## 🛠️ 管理命令

### 清除缓存
```bash
# 清除所有缓存
curl -X DELETE http://localhost:8080/api/cache/clear-all

# 清除指定缓存
curl -X DELETE http://localhost:8080/api/cache/clear/users
```

### 监控缓存
```bash
# 获取缓存统计
curl http://localhost:8080/api/cache/stats

# 检查键是否存在
curl http://localhost:8080/api/cache/exists/cache:users::all
```

## 🔍 故障排除

### 1. Redis 连接失败
```bash
# 检查 Redis 是否运行
redis-cli ping

# 检查端口是否被占用
netstat -an | grep 6379
```

### 2. 缓存不生效
- 确认 Redis 服务已启动
- 检查应用日志中的缓存相关错误
- 验证缓存注解是否正确

### 3. 序列化错误
- 确保实体类正确实现了序列化
- 检查 Jackson 配置

## 📈 性能优化

### 1. 监控缓存命中率
```bash
# 查看 Redis 统计信息
redis-cli info stats
```

### 2. 调整缓存策略
- 根据数据更新频率调整过期时间
- 使用合适的缓存键
- 避免缓存穿透

### 3. 内存管理
```bash
# 查看内存使用情况
redis-cli info memory
```

## 🎯 最佳实践

1. **合理使用缓存**: 只缓存频繁访问的数据
2. **及时清除缓存**: 数据更新时立即清除相关缓存
3. **监控性能**: 定期检查缓存效果
4. **备份数据**: 重要数据不要只依赖缓存

## 📚 更多信息

- [Redis 官方文档](https://redis.io/documentation)
- [Spring Cache 文档](https://docs.spring.io/spring-framework/reference/integration/cache.html)
- [项目缓存说明](REDIS_CACHE_README.md) 