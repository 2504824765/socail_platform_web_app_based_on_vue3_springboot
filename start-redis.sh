#!/bin/bash

# Redis 启动脚本
# 适用于 macOS 系统

echo "正在启动 Redis 服务..."

# 检查 Redis 是否已安装
if ! command -v redis-server &> /dev/null; then
    echo "Redis 未安装，正在使用 Homebrew 安装..."
    
    # 检查 Homebrew 是否已安装
    if ! command -v brew &> /dev/null; then
        echo "Homebrew 未安装，请先安装 Homebrew:"
        echo "https://brew.sh/"
        exit 1
    fi
    
    # 安装 Redis
    brew install redis
fi

# 检查 Redis 是否已在运行
if pgrep -x "redis-server" > /dev/null; then
    echo "Redis 服务已在运行"
    echo "Redis 端口: 6379"
    echo "可以使用 'redis-cli ping' 测试连接"
else
    echo "启动 Redis 服务..."
    redis-server --daemonize yes
    
    if [ $? -eq 0 ]; then
        echo "Redis 服务启动成功！"
        echo "Redis 端口: 6379"
        echo "可以使用 'redis-cli ping' 测试连接"
    else
        echo "Redis 服务启动失败"
        exit 1
    fi
fi

echo ""
echo "Redis 管理命令:"
echo "  启动: redis-server"
echo "  停止: redis-cli shutdown"
echo "  测试: redis-cli ping"
echo "  进入命令行: redis-cli"
echo "  查看状态: redis-cli info" 