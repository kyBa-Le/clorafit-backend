package com.project.product.dto.error;

import java.util.List;

public class Error {
    private String message;
    private String code;
    private List<ErrorDetails> details;

    public Error(String message, String code, List<ErrorDetails> details) {
        this.message = message;
        this.code = code;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ErrorDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorDetails> details) {
        this.details = details;
    }
}
