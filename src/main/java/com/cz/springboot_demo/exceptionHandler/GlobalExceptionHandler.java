package com.cz.springboot_demo.exceptionHandler;

import com.cz.springboot_demo.exception.*;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.io.IOException;

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

    @ExceptionHandler(IOException.class)
    public ResponseMessage ioException(IOException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(503, "IO exception", null);
    }

    @ExceptionHandler(FileSizeLimitExceededException.class)
    public ResponseMessage fileSizeLimitExceededException(FileSizeLimitExceededException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(504, "File Size Limit Exceeded", null);
    }

    @ExceptionHandler(InvalidFileFormatException.class)
    public ResponseMessage invalidFileFormatException(InvalidFileFormatException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(505, "Invalid File Format", null);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseMessage userAlreadyExistException(UserAlreadyExistException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(506, "User Already Exist", null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseMessage httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(507, "HttpMessageNotReadableException", null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMessage methodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(508, "MethodArgumentNotValidException", null);
    }

    @ExceptionHandler(OrderAlreadyExistException.class)
    public ResponseMessage orderAlreadyExistException(OrderAlreadyExistException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(509, "Order Already Exist", null);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseMessage orderNotFoundException(OrderNotFoundException e) {
        logger.error(e.getMessage(), e);
        return new ResponseMessage(510, "Order Not Found", null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseMessage handleException(Exception e) {
        logger.error(e.getMessage(), e); // 查看日志查看具体异常
        return new ResponseMessage(500, "Unknown exception", null);
    }

}
