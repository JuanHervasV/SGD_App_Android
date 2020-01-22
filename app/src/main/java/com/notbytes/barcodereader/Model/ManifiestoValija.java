package com.notbytes.barcodereader.Model;

public class ManifiestoValija {
        private String Mfto_Nro;
        private String Suc_cod;
        private String Mfto_anio;
        private String Valija;
        private int Total;
        private String Cerrado;

    public ManifiestoValija(String mfto_nro, String suc_cod, String mfto_anio){
            Mfto_Nro = mfto_nro;
            Suc_cod = suc_cod;
            Mfto_anio = mfto_anio;
    }

    public String valija(){return Valija;}

    public int total(){return Total;}

    public String cerrado(){return Cerrado;}

}
