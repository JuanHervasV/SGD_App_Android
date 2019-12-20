package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.notbytes.barcodereader.Model.Posts;
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
        //mJsonTxtView = findViewById(R.id.jsonText);
        //createPost();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_login:
                Intent i = new Intent(Logeo.this, MenuPrincipal.class);
                startActivity(i);
                break;
        }
    }

    private void createPost(){
        Posts posts = new Posts("49849294", "Juan", "Test123", "OK");
        Call<Posts> call = jsonPlaceHolderApi.createPost(posts);
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(!response.isSuccessful()){
                    TestApi.setText("Codigo:" + response.code());
                    return;
                }
                Posts postsResponse = response.body();
                String content = "";
                content += "Estado:" + postsResponse.estado() + "\n";
                content += "Mensaje:" + postsResponse.mensaje() + "\n";
                TestApi.append(content);
            }
            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                TestApi.setText(t.getMessage());
            }
        });

    }
}
