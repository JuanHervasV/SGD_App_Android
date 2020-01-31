package com.notbytes.barcodereader.Model;

public class ValijaValidar {
    private String codigo;
    private String Existe;
    private String ValijaID;
    private String Estado;
    private String Anio;
    private String Nro;
    private String Suc;

    public ValijaValidar(String Codigo){

        codigo = Codigo;
    }

    public String Existe(){return Existe;}

    public String ValijaID(){return ValijaID;}

    public String Estado(){return Estado;}

    public String Anio(){return Anio;}

    public String Nro(){return Nro;}

    public String Suc(){return Suc;}

}
