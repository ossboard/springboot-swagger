package com.konantech.spring.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class RestResponse implements Serializable {
    String result;
    int status;
    String message;
    long timestamp = System.currentTimeMillis();

    public RestResponse() {
        this.status = HttpStatus.OK.value();
        this.result = "success";
    }

    public RestResponse(Exception e) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.setResult("error");
        this.setMessage(e.getMessage());
    }
}

