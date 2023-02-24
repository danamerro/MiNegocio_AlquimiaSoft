package com.alquimiaSoft.miNegocio.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends Exception{

    protected CustomException() {
        super();
    }


    public CustomException(Throwable throwable) {
        super(throwable);
    }

    public abstract String getCode();

    public abstract String getDescription();

    public abstract HttpStatus getHttpStatus();
}
