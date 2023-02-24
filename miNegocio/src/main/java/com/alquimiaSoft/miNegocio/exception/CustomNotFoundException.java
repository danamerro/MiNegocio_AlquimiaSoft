package com.alquimiaSoft.miNegocio.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomNotFoundException extends CustomException{

    protected CustomNotFoundException(){}


    protected CustomNotFoundException(Throwable throwable){
        super(throwable);
    }


    @Override
    public HttpStatus getHttpStatus(){
        return HttpStatus.NOT_FOUND;
    }
}
