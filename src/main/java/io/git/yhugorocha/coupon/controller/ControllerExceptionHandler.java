package io.git.yhugorocha.coupon.controller;

import io.git.yhugorocha.coupon.exception.BusinessException;
import io.git.yhugorocha.coupon.exception.FieldError;
import io.git.yhugorocha.coupon.exception.StandardError;
import io.git.yhugorocha.coupon.exception.StandardFieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler{

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<StandardError> businessRuleHandler(BusinessException e){
        var error = new StandardError(HttpStatus.CONFLICT.value(), e.getMessage(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardFieldError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getFieldErrors().stream()
                .map(field -> new FieldError(field.getField(), field.getDefaultMessage()))
                .toList();

        return ResponseEntity.ok(
                StandardFieldError.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("Error validating fields")
                        .timestamp(System.currentTimeMillis())
                        .errors(fieldErrors)
                        .build()
        );
    }
}
