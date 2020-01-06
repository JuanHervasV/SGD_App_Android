package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.Vars;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Logeo extends AppCompatActivity {

    private APIRetrofitInterface jsonPlaceHolderApi;
    private TextView TestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

        TestApi = findViewById(R.id.TestApi);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiCyT/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_login:
                createPost();
                break;
        }
    }
    private void createPost(){
        Vars vars = new Vars("12345678", "12345");
        Call<Vars> call = jsonPlaceHolderApi.createPost(vars);
        call.enqueue(new Callback<Vars>() {
            @Override
            public void onResponse(Call<Vars> call, Response<Vars> response) {
                if(!response.isSuccessful()){
                    TestApi.setText("Codigo:" + response.code());
                    return;
                }
                Vars postsResponse = response.body();
                String content = "";
                content += "Estado:" + postsResponse.estado() + "\n";
                content += "Mensaje:" + postsResponse.mensaje() + "\n";
                TestApi.append(content);
                if(postsResponse.estado() == "true") {
                    Toast.makeText(Logeo.this, "Autentificación correcta", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Logeo.this, MenuPrincipal.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Logeo.this, "Fallo al autenticarse", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Vars> call, Throwable t) {
                TestApi.setText(t.getMessage());
            }
        });
    }
}