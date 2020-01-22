package com.notbytes.barcodereader.io;

import com.notbytes.barcodereader.Model.ManifiestoCerrar;
import com.notbytes.barcodereader.Model.ManifiestoContador;
import com.notbytes.barcodereader.Model.ManifiestoValija;
import com.notbytes.barcodereader.Model.Posts;
import com.notbytes.barcodereader.Model.ValidarGuia;
import com.notbytes.barcodereader.Model.ValidarMfto;
import com.notbytes.barcodereader.Model.ValijaAdicionar;
import com.notbytes.barcodereader.Model.ValijaCerrar;
import com.notbytes.barcodereader.Model.ValijaContador;
import com.notbytes.barcodereader.Model.ValijaStatus;
import com.notbytes.barcodereader.Model.Vars;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRetrofitInterface {

    @GET("posts")
    Call<List<Posts>> getPosts();

    @GET("Listar")
    Call<List<Vars>> getTiendaGamarra();

    @POST("Insertar")
    Call<Posts> createPost(@Body Posts posts);

    @POST("Login")
    Call<Vars> createPost(@Body Vars vars);

    @POST("ValidarMfto")
    Call<ValidarMfto> createPost(@Body ValidarMfto validarMfto);

    @POST("ValijaAdicionar")
    Call<ValijaAdicionar> createPost(@Body ValijaAdicionar valijaAdicionar);

    @POST("ValidarGuia")
    Call<ValidarGuia> createPost(@Body ValidarGuia validarGuia);

    @POST("ValijaCerrar")
    Call<ValijaCerrar> createPost(@Body ValijaCerrar valijaCerrar);

    @POST("ValijaStatus")
    Call<ValijaStatus> createPost(@Body ValijaStatus valijaStatus);

    @POST("ValijaContador")
    Call<ValijaContador> createPost(@Body ValijaContador valijaContador);

    @POST("MftoCerrar")
    Call<ManifiestoCerrar> createPost(@Body ManifiestoCerrar manifiestoCerrar);

    @POST("MftoContador")
    Call<ManifiestoContador> createPost(@Body ManifiestoContador manifiestoContador);

    @POST("MftoValijas")
    Call<ManifiestoValija> createPost(@Body ManifiestoValija manifiestoValija);

}
