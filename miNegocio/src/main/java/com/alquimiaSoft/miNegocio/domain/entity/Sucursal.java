package com.alquimiaSoft.miNegocio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "SUCURSAL")
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUCURSAL")
    private Long idSucursal;
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "SUCURSAL_MATRIZ")
    private Boolean sucursalMatriz;

    public Boolean getSucursalMatriz() {
        return sucursalMatriz;
    }

    public void setSucursalMatriz(Boolean sucursalMatriz) {
        this.sucursalMatriz = sucursalMatriz;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
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
