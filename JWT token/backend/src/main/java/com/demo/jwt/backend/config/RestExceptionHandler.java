package com.demo.jwt.backend.config;

import com.demo.jwt.backend.exception.AppException;
import com.demo.jwt.backend.exception.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity.status(ex.getCode())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }
}
