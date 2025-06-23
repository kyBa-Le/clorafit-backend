package com.project.auth.presentation.restfulApi;

import com.project.auth.domain.exception.ExistedPhoneException;
import com.project.auth.infrastructure.util.DateTimeFormater;
import com.project.auth.presentation.restfulApi.dto.error.CustomError;
import com.project.auth.presentation.restfulApi.dto.error.CustomErrorResponse;
import com.project.auth.presentation.restfulApi.dto.error.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ArrayList<ErrorDetails> errorDetails = new ArrayList<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            ErrorDetails errorDetail = new ErrorDetails(fieldError.getField(), fieldError.getDefaultMessage());
            errorDetails.add(errorDetail);
        }

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                new CustomError("BAD_ARGUMENT",
                        "The request body is invalid",
                        errorDetails,
                        DateTimeFormater.SimpleDateFormatConverter(new Date()))
        );

        return ResponseEntity.status(400).body(customErrorResponse);
    }

    @ExceptionHandler(value = ExistedPhoneException.class)
    public ResponseEntity<CustomErrorResponse> handleExistedPhoneException(ExistedPhoneException exception) {
        ArrayList<ErrorDetails> errorDetails = new ArrayList<>();
        errorDetails.add(new ErrorDetails(exception.getField(), exception.getMessage()));

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                new CustomError("EXISTED",
                        "The request body is invalid",
                        errorDetails,
                        DateTimeFormater.SimpleDateFormatConverter(new Date()))
        );

        return ResponseEntity.status(400).body(customErrorResponse);
    }

}
