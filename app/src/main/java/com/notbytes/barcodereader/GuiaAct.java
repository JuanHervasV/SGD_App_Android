package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.ValidarGuia;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuiaAct extends AppCompatActivity {
    private TextView Manifiesto;
    private TextView Valija;
    private EditText Guia;
    private APIRetrofitInterface jsonPlaceHolderApi;
    private TextView Mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        //RecuperarDatos();
        //createPost();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar:
                createPost();
                break;
        }
    }

    private void createPost(){
        Guia = findViewById(R.id.txtGuia);
        Mensaje = findViewById(R.id.txtMensaje);

        final String Gui = Guia.getText().toString();

        //Titulo = findViewById(R.id.txtTitulo);
        //Aqui enviar los datos
        //String resul = mTvResult.getText().toString();
        ValidarGuia validarGuia = new ValidarGuia("2014","00000046","PEAQP01","0020120232");
        Call<ValidarGuia> call = jsonPlaceHolderApi.createPost(validarGuia);
        call.enqueue(new Callback<ValidarGuia>() {
            @Override
            public void onResponse(Call<ValidarGuia> call, Response<ValidarGuia> response) {
                if(!response.isSuccessful()){
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ValidarGuia postsResponse = response.body();

                    String Estado = postsResponse.estado();
                    String Guia = postsResponse.Guias();
                    Toast.makeText(getApplicationContext(),"Datos ingresado exitosamente",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(GuiaAct.this, CerrarValijaAct.class);
                    Bundle c = new Bundle();
                    c.putString("Estado", Estado);
                    c.putString("Guia", Guia);
                    //i.putExtras(c);
                    Mensaje.append(""+response.body());
                    //Mensaje.append(""+response.headers());
                    startActivity(i);
                    //Titulo.append(""+postsResponse.estado());
                    return;
                }
                ValidarGuia postsResponse = response.body();

                String Estado = postsResponse.estado();
                String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(),"Dato ingresado exitosamente",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(GuiaAct.this, CerrarValijaAct.class);
                    Bundle c = new Bundle();
                c.putString("Estado", Estado);
                c.putString("Guia", Guia);

                i.putExtras(c);
                Mensaje.append(""+postsResponse.estado());
                Mensaje.append(""+postsResponse.Guias());
                startActivity(i);
                //Titulo.append(""+postsResponse.estado());
                return;
            }
            @Override
            public void onFailure(Call<ValidarGuia> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fallo al ingresar los datos, compruebe su red.",Toast.LENGTH_SHORT).show();
                //Titulo.setText(t.getMessage());
                return;
            }
        });
    }

    public void RecuperarDatos(){
        //Llamar datos ---------------------------------------
        Bundle b = getIntent().getExtras();
        final String Valijas = b.getString("Valijas");
        final String Mfto = b.getString("Mft");
        final String MftoAnio = b.getString("MftoAnio");
        final String MftoNro = b.getString("MftoNro");
        final String Suc = b.getString("Suc");
        final String PaisDes = b.getString("PaisDes");
        final String CiuDes = b.getString("CiuDes");
        final String Estado = b.getString("Estado");
        //----------------------------------------------------
        Manifiesto = findViewById(R.id.txtMfto);
        Manifiesto.setText(Mfto);
        Valija = findViewById(R.id.txtValija);
        Valija.setText(Valijas);
        Guia = findViewById(R.id.txtGuia);
        String Guias = Guia.getText().toString();

    }


}
