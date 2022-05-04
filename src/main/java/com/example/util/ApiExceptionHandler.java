package com.example.util;

import com.example.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ResponseMessage<String>> handleFailException(ApiException apiException) {
        ResponseMessage<String> exceptionResponseMessage = new ResponseMessage<>();
        exceptionResponseMessage.setMessage(apiException.getMessage());
        exceptionResponseMessage.setValue(apiException.toString());
        exceptionResponseMessage.setStatus(apiException.getHttpStatus().toString());
        String threwClassName = apiException.getStackTrace()[0].getClassName();
        log.warn(threwClassName + " : " + apiException.getMessage());
        return new ResponseEntity<>(exceptionResponseMessage, apiException.getHttpStatus());
    }
}
