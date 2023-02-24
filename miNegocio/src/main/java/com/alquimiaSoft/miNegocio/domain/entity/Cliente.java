package com.alquimiaSoft.miNegocio.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTE")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long idCliente;
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;
    @Column(name = "NUMERO_IDENTIFICACION")
    private Integer numeroIdentificacion;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "CORREO")
    private String correo;
    @Column(name = "NUMERO_CELULAR")
    private Integer numeroCelular;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sucursal> direccionesAsociadas;



    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public int getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(int numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(int numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public List<Sucursal> getDireccionesAsociadas() {
        return direccionesAsociadas;
    }

    public void setDireccionesAsociadas(List<Sucursal> direccionesAsociadas) {
        this.direccionesAsociadas = direccionesAsociadas;
    }
}
