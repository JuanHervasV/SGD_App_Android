package com.notbytes.barcodereader.Model;

public class Vars {
    private String Codigo_barra;
    private String Usuario;
    private String Coordenadas;
    private String Estado;
    private String estado;
    private String mensaje;
    private String Login;
    private String Password;

    public String mensaje() {
        return mensaje;
    }
    public String estado() {
        return estado;
    }
    public Vars(String login, String password) {
        Login = login;
        Password = password;
    }
}
