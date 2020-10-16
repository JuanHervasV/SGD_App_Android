package com.notbytes.barcodereader.Model;

public class PesosManifiesto {

    private String Mfto;
    private int Peso;
    private String PesoTotal;

    public PesosManifiesto(String mfto){
        Mfto = mfto;
    }

    public String PesoTotal(){return PesoTotal;}
}
