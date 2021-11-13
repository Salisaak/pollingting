package com.quickpoll.pollingapi.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.quickpoll.pollingapi.dto.error.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.quickpoll.pollingapi.dto.error.ErrorDetail;
import com.quickpoll.pollingapi.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler extends ResourceNotFoundException {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Jawn Not Found");
        errorDetail.setDetail(exception.getMessage());
        errorDetail.setDeveloperMessage(exception.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorDetail handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");

        if(requestPath == null) {
            requestPath = request.getRequestURI();
        }

        errorDetail.setTitle("Validation Failed");
        errorDetail.setDetail("Input validation failed");
        errorDetail.setDeveloperMessage(manve.getClass().getName());

        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrors) {

            List<ValidationError> validationErrorList = errorDetail.getErrors().
                    get(fe.getField());
            if(validationErrorList == null) {
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }

            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }

        return errorDetail;
    }

}



