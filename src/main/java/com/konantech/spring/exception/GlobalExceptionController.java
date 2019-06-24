package com.konantech.spring.exception;

import com.konantech.spring.response.ObjectResponse;
import com.konantech.spring.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionController implements Serializable {
    @ExceptionHandler(value = FieldException.class)
    @ResponseBody
    public ObjectResponse<FieldException> handleFieldException(FieldException ex) {
        return new ObjectResponse<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RestException.class)
    @ResponseBody
    public Object handleKonanException(RestException ex) {

        return new ResponseEntity<>(new RestResponse(ex), HttpStatus.BAD_REQUEST);

//        return new ObjectResponse<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public ObjectResponse<NotFoundException> handleKonanException(NotFoundException ex) {
        return new ObjectResponse<>(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception ex) {
        return new ResponseEntity<>(new RestResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
