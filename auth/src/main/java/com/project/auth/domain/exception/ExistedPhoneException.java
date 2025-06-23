package com.project.auth.domain.exception;

import lombok.Getter;

@Getter
public class ExistedPhoneException extends RuntimeException {
    public String field;

    public ExistedPhoneException() {
        super("Phone already existed");
        this.field = "phone";
    }

}
