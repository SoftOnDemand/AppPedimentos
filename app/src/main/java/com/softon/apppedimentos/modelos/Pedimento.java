package com.softon.apppedimentos.modelos;

public class Pedimento {

    int idPedimento;
    String noPedimento;
    int idEjecutivo;
    String nomEjecutivo;

    public Pedimento(String noPedimento, int idPedimento,int idEjecutivo,String nomEjecutivo){
        this.idPedimento = idPedimento;
        this.idEjecutivo = idEjecutivo;
        this.nomEjecutivo = nomEjecutivo;
        this.noPedimento = noPedimento;
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

    @Override
    public String toString() {
        return "Pedimento{" +
                "idPedimento=" + idPedimento +
                ", noPedimento='" + noPedimento + '\'' +
                ", idEjecutivo=" + idEjecutivo +
                ", nomEjecutivo='" + nomEjecutivo + '\'' +
                '}';
    }
}
