package com.softon.apppedimentos.modelos;

public class Sucursal {

    String nomSucursal;
    int idSucursal;

    public Sucursal(String nomSucursal,int idSucursal){
        this.idSucursal = idSucursal;
        this.nomSucursal = nomSucursal;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public String toString() {
        return getNomSucursal();
    }
}
