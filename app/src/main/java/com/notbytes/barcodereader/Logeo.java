package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
    //private TextView TestApi;
    private TextView LoginText;
    private TextView PasswordText;
    private String Usuario;
    private String Pass;
    private Button Login_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);
        Login_b = findViewById(R.id.button_login_login);
        LoginText = findViewById(R.id.editText_login_username);
        PasswordText = findViewById(R.id.editText_login_password);

        //TestApi = findViewById(R.id.TestApi);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiCyT/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        this.Usuario = LoginText.getText().toString();
        this.Pass = PasswordText.getText().toString();
        onTouch();
    }
    public void onTouch() {
        Login_b = findViewById(R.id.button_login_login);
        Login_b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_corners);
                    //v.setBackgroundColor(Color.parseColor("@drawable/rounded_corners"));
                }
                return false;
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_login:
                createPost();
                break;
        }
    }
    private void createPost(){

        Vars vars = new Vars(LoginText.getText().toString(), PasswordText.getText().toString());

        Call<Vars> call = jsonPlaceHolderApi.createPost(vars);
        call.enqueue(new Callback<Vars>() {
            @Override
            public void onResponse(Call<Vars> call, Response<Vars> response) {
                if(!response.isSuccessful()){
                    //TestApi.setText("Codigo:" + response.code());
                    return;
                }
                Vars postsResponse = response.body();
                String content = "";
                content += "Estado:" + postsResponse.estado() + "\n";
                content += "Mensaje:" + postsResponse.mensaje() + "\n";
                //TestApi.append(content);
                if(postsResponse.estado() == "true") {
                    Toast.makeText(Logeo.this, "Autentificación correcta", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Logeo.this, Pre_estado.class);
                    RecuperarUsuario();
                    //startActivity(i);
                }
                else{
                    Toast.makeText(Logeo.this, "Fallo al autenticarse", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Vars> call, Throwable t) {
                Toast.makeText(Logeo.this, "Error de red, revise su conexión a internet.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RecuperarUsuario(){
        String passingdata = LoginText.getText().toString();
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        Intent i = new Intent(Logeo.this, MenuPrincipal.class);
        //Intent a = new Intent(Logeo.this, Pre_estado.class);
        Bundle c = new Bundle();
        c.putString("Key", passingdata);
        i.putExtras(c);
        startActivity(i);
    }

    public String getUsuario() {
        return this.Usuario;
    }

    public String getPassword() {
        return this.Pass;
    }
}