package com.alquimiaSoft.miNegocio.exception.cliente.errorMetodo;

import com.alquimiaSoft.miNegocio.exception.CustomException;
import com.alquimiaSoft.miNegocio.exception.constants.ExceptionCode;
import org.springframework.http.HttpStatus;

public class DeleteClienteException extends CustomException {

    public DeleteClienteException(Throwable throwable){
        super(throwable);
    }

    @Override
    public String getCode(){
        return ExceptionCode.CODE_07;
    }

    @Override
    public String getDescription(){
        return ExceptionCode.MESSAGE_07;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
