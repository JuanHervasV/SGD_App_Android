package com.notbytes.barcodereader.Model;

public class ValijaCerrar {
    private String estado;
    private String mensaje;
    private int IDValija;

    public ValijaCerrar(int iDValija){
        IDValija = iDValija;
    }

    public String Estado(){return estado;}

    public String Mensaje(){return mensaje;}

}
