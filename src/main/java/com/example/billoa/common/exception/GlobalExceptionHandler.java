package com.example.billoa.common.exception;

import com.example.billoa.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.NoSuchAlgorithmException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoSuchAlgorithmException.class)
    public Result<String> handler(NoSuchAlgorithmException e)
    {
        log.error("加密算法异常----------"+e);
        return  Result.fail(e.getMessage());
    }
}
