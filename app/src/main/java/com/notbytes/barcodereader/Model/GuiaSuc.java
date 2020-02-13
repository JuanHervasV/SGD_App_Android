package com.notbytes.barcodereader.Model;

public class GuiaSuc {
    private String IDGuia;
    private String SUC_COD;
    private String MFT_ANO;
    private String MFT_NRO;
    private String GUIA_DET_NRO_REF;

    public GuiaSuc(String idguia){
        IDGuia = idguia;
    }

    public String suc_code(){return SUC_COD;}
    public String mft_ano(){return MFT_ANO;}
    public String mft_nro(){return MFT_NRO;}
    public String guia_detid(){return GUIA_DET_NRO_REF;};
}
