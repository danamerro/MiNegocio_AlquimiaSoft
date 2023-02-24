package com.alquimiaSoft.miNegocio.exception.sucursal.errorDB;

import com.alquimiaSoft.miNegocio.exception.CustomInternalServerException;
import com.alquimiaSoft.miNegocio.exception.constants.ExceptionCode;

public class FoundSucursalExistenteException extends CustomInternalServerException {

    @Override
    public String getCode(){
        return ExceptionCode.CODE_09;
    }

    @Override
    public String getDescription(){
        return ExceptionCode.MESSAGE_09;
    }
}
