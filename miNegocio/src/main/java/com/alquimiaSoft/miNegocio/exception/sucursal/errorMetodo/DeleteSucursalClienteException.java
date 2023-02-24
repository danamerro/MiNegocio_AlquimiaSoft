package com.alquimiaSoft.miNegocio.exception.sucursal.errorMetodo;

import com.alquimiaSoft.miNegocio.exception.CustomException;
import com.alquimiaSoft.miNegocio.exception.constants.ExceptionCode;
import org.springframework.http.HttpStatus;

public class DeleteSucursalClienteException extends CustomException {

    public DeleteSucursalClienteException(Throwable throwable){
        super(throwable);
    }

    @Override
    public String getCode(){
        return ExceptionCode.CODE_14;
    }

    @Override
    public String getDescription(){
        return ExceptionCode.MESSAGE_14;
    }

    @Override
    public HttpStatus getHttpStatus(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
