package com.alquimiaSoft.miNegocio.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomInternalServerException extends CustomException{

    protected CustomInternalServerException(){

    }

    protected CustomInternalServerException(Throwable throwable){
        super(throwable);
    }


    @Override
    public HttpStatus getHttpStatus(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
