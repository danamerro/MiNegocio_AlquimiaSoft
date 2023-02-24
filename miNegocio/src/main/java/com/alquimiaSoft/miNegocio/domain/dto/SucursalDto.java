package com.alquimiaSoft.miNegocio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
public class SucursalDto {

    private Long idSucursal;
    @NotNull(message = "debe tener una provincia")
    private String provincia;
    @NotNull(message = "debe tener una ciudad")
    private String ciudad;
    @NotNull(message = "debe contener una direccion")
    @Pattern(regexp ="^[a-zA-Z0-9\\s]+$")
    private String direccion;
    @NotNull(message = "tiene que validar si es sucursal matriz")
    private Boolean sucursalMatriz;

    public Boolean getSucursalMatriz() {
        return sucursalMatriz;
    }

    public void setSucursalMatriz(Boolean sucursalMatriz) {
        this.sucursalMatriz = sucursalMatriz;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
