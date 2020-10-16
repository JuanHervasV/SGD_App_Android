package com.notbytes.barcodereader.Model;

public class PesoValija {

    private String ValijaNum;
    private String Peso;
    private String valijaNum;
    private String estado;
    private String mensaje;

    public PesoValija(String valijaNum, String peso){
        ValijaNum = valijaNum;
        Peso = peso;
    }

    public String estado(){return estado;}
    public String mensaje(){return mensaje;}

}
