package com.softon.apppedimentos.conexionWS;

public class Constantes {

    public static final String PROTOCOLO = "http";

    private static String DIRECCION = PROTOCOLO+"://pedimentos.softon.com.mx/api/";
    private static String GRUPOUSER = DIRECCION+"user";
//    private static String GRUPOSUCURSAL = DIRECCION+"sucursal";
    private static String GRUPOPEDIMENTO = DIRECCION+"pedimento";


    public static final String login =  GRUPOUSER+"/login";

    public static final String getPedimentos =  GRUPOPEDIMENTO+"/";
    public static final String editarPedimentos =  GRUPOPEDIMENTO+"/";
    public static final String buscarPedimentos =  GRUPOPEDIMENTO+"/";
    public static final String crearPedimentos =  GRUPOPEDIMENTO+"/crear";
    public static final String eliminarPedimentos =  GRUPOPEDIMENTO+"/";


}
