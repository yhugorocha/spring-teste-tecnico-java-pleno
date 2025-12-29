package io.git.yhugorocha.coupon.controller;

import io.git.yhugorocha.coupon.exception.BusinessException;
import io.git.yhugorocha.coupon.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<StandardError> businessRuleHandler(BusinessException e){
        var error = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
