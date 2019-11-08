package com.konantech.spring.exception;

import com.konantech.spring.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception ex) {
        return new ResponseEntity<>(new RestResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
