package com.notbytes.barcodereader.Model;

public class AdicionarGuias {
    private int IDGuiaValija;
    private int IDValija;
    private String IDGuiaDet;
    private String IDUser;
    private String estado;
    private String mensaje;

    public AdicionarGuias(int idguiavalija, int idvalija, String idguiadet, String iduser){

        IDGuiaValija = idguiavalija;
        IDValija = idvalija;
        IDGuiaDet = idguiadet;
        IDUser = iduser;

    }

    public String Estado(){return estado;}

    public String Mensaje(){return mensaje;}
}
