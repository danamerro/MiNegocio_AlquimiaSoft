package com.alquimiaSoft.miNegocio.exception.cliente.errorDB;

import com.alquimiaSoft.miNegocio.exception.CustomException;
import com.alquimiaSoft.miNegocio.exception.constants.ExceptionCode;
import org.springframework.http.HttpStatus;

public class FoundClienteExistenteException extends CustomException {

    @Override
    public String getCode(){
        return ExceptionCode.CODE_15;
    }

    @Override
    public String getDescription(){
        return ExceptionCode.MESSAGE_15;
    }

    @Override
    public HttpStatus getHttpStatus(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
