package com.konantech.spring.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonIgnoreProperties({"headers", "statusCode", "statusCodeValue"})
@Data
public class RestResponse implements Serializable {
    String result;
    int status;
    String message="";
    long timestamp = System.currentTimeMillis();

    public RestResponse() {
        this.status = HttpStatus.OK.value();
        this.result = "SUCCESS";
    }

    public RestResponse(Exception e) {
        this.status = HttpStatus.BAD_REQUEST.value();
        this.setResult("ERROR");
        this.setMessage(e.getMessage());
    }
}

