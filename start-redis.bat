@echo off
chcp 65001 >nul
echo 正在启动 Redis 服务...

REM 检查 Redis 是否已安装
where redis-server >nul 2>&1
if %errorlevel% neq 0 (
    echo Redis 未安装，请先安装 Redis
    echo 下载地址: https://redis.io/download
    echo 或者使用 Chocolatey: choco install redis-64
    pause
    exit /b 1
)

REM 检查 Redis 是否已在运行
tasklist /FI "IMAGENAME eq redis-server.exe" 2>NUL | find /I /N "redis-server.exe">NUL
if "%ERRORLEVEL%"=="0" (
    echo Redis 服务已在运行
    echo Redis 端口: 6379
    echo 可以使用 'redis-cli ping' 测试连接
) else (
    echo 启动 Redis 服务...
    start /B redis-server --daemonize yes
    
    if %errorlevel% equ 0 (
        echo Redis 服务启动成功！
        echo Redis 端口: 6379
        echo 可以使用 'redis-cli ping' 测试连接
    ) else (
        echo Redis 服务启动失败
        pause
        exit /b 1
    )
)

echo.
echo Redis 管理命令:
echo   启动: redis-server
echo   停止: redis-cli shutdown
echo   测试: redis-cli ping
echo   进入命令行: redis-cli
echo   查看状态: redis-cli info
pause 