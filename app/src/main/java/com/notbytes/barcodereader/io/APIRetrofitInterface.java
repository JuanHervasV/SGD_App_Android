package com.notbytes.barcodereader.io;

import com.notbytes.barcodereader.Model.Vars;
import com.notbytes.barcodereader.Model.Posts;

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

}
