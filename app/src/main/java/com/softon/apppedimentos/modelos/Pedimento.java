package com.softon.apppedimentos.modelos;

import java.io.Serializable;

public class Pedimento implements Serializable {

    int idPedimento;
    String noPedimento;
    int idEjecutivo;
    String nomEjecutivo;
    String fechaArribo;
    int estado;
    String fechaCreacion;
    int idSucursal;
    String sucursal;

    public Pedimento(String noPedimento, int idPedimento,int idEjecutivo,String nomEjecutivo,String fechaArribo,int estado,String fechaCreacion,int idSucursal, String sucursal){
        this.idPedimento = idPedimento;
        this.idEjecutivo = idEjecutivo;
        this.nomEjecutivo = nomEjecutivo;
        this.noPedimento = noPedimento;
        this.fechaArribo = fechaArribo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.idSucursal = idSucursal;
        this.sucursal = sucursal;
    }


    public int getIdPedimento() {
        return idPedimento;
    }

    public void setIdPedimento(int idPedimento) {
        this.idPedimento = idPedimento;
    }

    public String getNoPedimento() {
        return noPedimento;
    }

    public void setNoPedimento(String noPedimento) {
        this.noPedimento = noPedimento;
    }

    public int getIdEjecutivo() {
        return idEjecutivo;
    }

    public void setIdEjecutivo(int idEjecutivo) {
        this.idEjecutivo = idEjecutivo;
    }

    public String getNomEjecutivo() {
        return nomEjecutivo;
    }

    public void setNomEjecutivo(String nomEjecutivo) {
        this.nomEjecutivo = nomEjecutivo;
    }

    public String getFechaArribo() {
        return fechaArribo;
    }

    public void setFechaArribo(String fechaArribo) {
        this.fechaArribo = fechaArribo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return "Pedimento{" +
                "idPedimento=" + idPedimento +
                ", noPedimento='" + noPedimento + '\'' +
                ", idEjecutivo=" + idEjecutivo +
                ", nomEjecutivo='" + nomEjecutivo + '\'' +
                ", fechaArribo='" + fechaArribo + '\'' +
                ", estado=" + estado +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", idSucursal=" + idSucursal +
                ", sucursal='" + sucursal + '\'' +
                '}';
    }
}
