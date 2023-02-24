package com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo;


import com.alquimiaSoft.miNegocio.exception.CustomException;
import com.alquimiaSoft.miNegocio.exception.constants.ExceptionCode;
import org.springframework.http.HttpStatus;

public class PutClienteException extends CustomException {

    public PutClienteException(Throwable throwable){
        super(throwable);
    }

    @Override
    public String getCode(){
        return ExceptionCode.CODE_06;
    }

    @Override
    public String getDescription(){
        return ExceptionCode.MESSAGE_06;
    }

    @Override
    public HttpStatus getHttpStatus(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
