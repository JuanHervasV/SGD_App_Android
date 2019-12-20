package com.notbytes.barcodereader.Model;

public class Posts {
    private String Codigo_barra;
    private String Usuario;
    private String Coordenadas;
    private String Estado;
    private String estado;
    private String mensaje;

    public String mensaje() {
        return mensaje;
    }
    public String estado() {
        return estado;
    }
    public Posts(String codigo_barra, String usuario, String coordenadas, String estado) {
        Codigo_barra = codigo_barra;
        Usuario = usuario;
        Coordenadas = coordenadas;
        Estado = estado;
    }
}
