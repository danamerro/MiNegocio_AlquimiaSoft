package com.alquimiaSoft.miNegocio.exception.cliente.errorDB;

import com.alquimiaSoft.miNegocio.exception.CustomNotFoundException;
import com.alquimiaSoft.miNegocio.exception.constants.ExceptionCode;

public class MissingClienteByNumeroIdentificacionException extends CustomNotFoundException {

    @Override
    public String getCode(){
        return ExceptionCode.CODE_01;
    }

    @Override
    public String getDescription(){
        return ExceptionCode.MESSAGE_01;
    }
}
