package com.waes.diffapp.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ErrorMessage {
    private Date timestamp;
    private String message;
    private HttpStatus httpStatus;

    public ErrorMessage(String message, HttpStatus httpStatus) {
        this(message, httpStatus, new Date());
    }

    public ErrorMessage(String message, HttpStatus httpStatus, Date timestamp) {
        this.timestamp = timestamp;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
