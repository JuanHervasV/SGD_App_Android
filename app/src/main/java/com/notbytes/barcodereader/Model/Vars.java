package com.notbytes.barcodereader.Model;

public class Vars {
    private String Codigo_barra;
    private String Usuario;
    private String Coordenadas;
    private String Estado;
    private String estado;
    private String mensaje;
    private String NroIdentificacion;
    private String Password;

    public String mensaje() {
        return mensaje;
    }
    public String estado() {
        return estado;
    }
    public Vars(String nroIdentificacion, String password) {
        NroIdentificacion = nroIdentificacion;
        Password = password;
    }
}
