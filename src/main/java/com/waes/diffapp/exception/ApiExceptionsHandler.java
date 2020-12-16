package com.waes.diffapp.exception;

import static com.waes.diffapp.util.MessageSourceUtil.getLocalizedMessage;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(getLocalizedMessage("diff.session.no.side.content"), BAD_REQUEST);
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(errorMessage);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException() {
        ErrorMessage errorMessage = new ErrorMessage(getLocalizedMessage("diff.session.not.found"), NOT_FOUND);
        return ResponseEntity.status(errorMessage.getHttpStatus()).body(errorMessage);
    }
}
