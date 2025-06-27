package com.project.auth.presentation.restfulApi.dto.base;

import com.project.auth.infrastructure.util.DateTimeFormater;
import com.project.auth.presentation.restfulApi.dto.error.CustomError;
import com.project.auth.presentation.restfulApi.dto.error.ErrorDetails;
import java.util.ArrayList;
import java.util.Date;

public class CustomErrorResponse{
    public final CustomError customError;

    public CustomErrorResponse(CustomError customError) {
        this.customError = customError;
    }

    public static CustomErrorResponse build(String code, String message, String field) {
        ArrayList<ErrorDetails> details = new ArrayList<>();
        details.add(new ErrorDetails(field, message));

        return new CustomErrorResponse(
                new CustomError(
                        code,
                        message,
                        details,
                        DateTimeFormater.SimpleDateFormatConverter(new Date())
                )
        );
    }
}
