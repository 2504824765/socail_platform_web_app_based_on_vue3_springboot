package com.cz.springboot_demo.exceptionHandler;

import com.cz.springboot_demo.exception.UserNotFoundException;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

// Since 2025/5/12 by CZ
@RestControllerAdvice // 专门用于做统一处理
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 在捕获异常时，会扫描RestControllerAdvice文件下所有的ExceptionHandler，特定异常放上面，默认处理放在最下面兜底
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseMessage userNotFoundException(UserNotFoundException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(501, "User not fond", null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseMessage authenticationException(AuthenticationException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(502, "Authentication failed", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseMessage handleException(Exception e) {
        logger.error(e.getMessage(), e); // 查看日志查看具体异常
        return new ResponseMessage(500, "Unknown exception", null);
    }

}
