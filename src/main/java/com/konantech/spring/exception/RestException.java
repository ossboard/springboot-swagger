package com.konantech.spring.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties({"stackTrace", "cause", "localizedMessage", "suppressed", "suppressedExceptions"})
public class RestException extends Exception implements Serializable {
    private int code;

    @JsonProperty("status")
    private int status = 400;

    @JsonProperty("timestamp")
    private long timestamp = System.currentTimeMillis();

    @JsonProperty("message")
    private Object message = this.getDetailMessage();

    @JsonProperty("path")
    private String path = (this.getStackTrace())[0].getMethodName();

    @JsonProperty("exception")
    private String exception = (this.getStackTrace())[0].getClassName();

    @JsonProperty("error")
    private String error;


    public RestException() {
    }

    public RestException(Exception e) {
        super(e);
        this.message = e.getMessage();
    }

    public RestException(int code) {
        this(code, null);
    }

    public RestException(int code, String message) {
        super(message);
        this.code = code;
    }

    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String getDetailMessage() {
        return super.getMessage();
    }
}
