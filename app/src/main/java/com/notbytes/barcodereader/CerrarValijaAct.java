package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.ValijaCerrar;
import com.notbytes.barcodereader.Model.ValijaContador;
import com.notbytes.barcodereader.Model.ValijaStatus;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CerrarValijaAct extends AppCompatActivity {
    private TextView Titulo;
    private TextView Manifiesto;
    private TextView Guia;
    private TextView Valija;
    private Button CerrarValija;
    private Button Volver;
    private APIRetrofitInterface jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_valija);
        //RecuperarDatos()

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);
        //cerrarValija();
        ValijasContador();

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCerrar:
                cerrarValija();
                cerrarStatus();
                break;
        }
    }

    public void RecuperarDatos(){
        //Llamar datos ---------------------------------------
        Bundle b = getIntent().getExtras();
        final String Estado = b.getString("Estado");
        final String Guias = b.getString("Guia");
        //----------------------------------------------------
        Manifiesto = findViewById(R.id.txtMfto);
        //Manifiesto.setText(Mfto);
        Valija = findViewById(R.id.txtValija);
        //Valija.setText(Valijas);
        Guia = findViewById(R.id.txtGuia);
        Guia.append(Guias);

    }

    private void cerrarValija(){

        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        //String resul = mTvResult.getText().toString();
        ValijaCerrar valijaCerrar = new ValijaCerrar(145);
        Call<ValijaCerrar> call = jsonPlaceHolderApi.createPost(valijaCerrar);
        call.enqueue(new Callback<ValijaCerrar>() {
            @Override
            public void onResponse(Call<ValijaCerrar> call, Response<ValijaCerrar> response) {
                if(!response.isSuccessful()){
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ValijaCerrar postsResponse = response.body();

                    //String Estado = postsResponse.estado();
                    //String Guia = postsResponse.Guias();
                    Toast.makeText(getApplicationContext(),"Datos ingresado exitosamente 1",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
                    Bundle c = new Bundle();
                    //c.putString("Estado", Estado);
                    //c.putString("Guia", Guia);
                    //i.putExtras(c);
                    //Mensaje.append(""+response.body());
                    //Mensaje.append(""+response.headers());
                    startActivity(i);
                    //Titulo.append(""+postsResponse.estado());
                    return;
                }
                ValijaCerrar postsResponse = response.body();

                //String Estado = postsResponse.estado();
                //String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(),"Dato ingresado exitosamente 1",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
                Bundle c = new Bundle();
                //c.putString("Estado", Estado);
                //c.putString("Guia", Guia);
                i.putExtras(c);
                //Mensaje.append(""+postsResponse.estado());
                //Mensaje.append(""+postsResponse.Guias());
                startActivity(i);
                //Titulo.append(""+postsResponse.estado());
                return;
            }
            @Override
            public void onFailure(Call<ValijaCerrar> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fallo al ingresar los datos, compruebe su red.",Toast.LENGTH_SHORT).show();
                //Titulo.setText(t.getMessage());
                return;
            }
        });
    }

    private void cerrarStatus(){
        //Aqui enviar los datos-------------------------------------------------------------------------------------------

        ValijaStatus valijaStatus = new ValijaStatus("145", "123");
        Call<ValijaStatus> call = jsonPlaceHolderApi.createPost(valijaStatus);
        call.enqueue(new Callback<ValijaStatus>() {
            @Override
            public void onResponse(Call<ValijaStatus> call, Response<ValijaStatus> response) {
                if(!response.isSuccessful()){
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ValijaStatus postsResponse = response.body();

                    //String Estado = postsResponse.estado();
                    //String Guia = postsResponse.Guias();
                    Toast.makeText(getApplicationContext(),"Datos ingresado exitosamente 2",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
                    Bundle c = new Bundle();
                    //c.putString("Estado", Estado);
                    //c.putString("Guia", Guia);
                    //i.putExtras(c);
                    //Mensaje.append(""+response.body());
                    //Mensaje.append(""+response.headers());
                    startActivity(i);
                    //Titulo.append(""+postsResponse.estado());
                    return;
                }
                ValijaStatus postsResponse = response.body();

                //String Estado = postsResponse.estado();
                //String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(),"Dato ingresado exitosamente 2",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
                Bundle c = new Bundle();
                //c.putString("Estado", Estado);
                //c.putString("Guia", Guia);
                i.putExtras(c);
                //Mensaje.append(""+postsResponse.estado());
                //Mensaje.append(""+postsResponse.Guias());
                startActivity(i);
                //Titulo.append(""+postsResponse.estado());
                return;
            }
                @Override
            public void onFailure(Call<ValijaStatus> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fallo al ingresar los datos, compruebe su red.",Toast.LENGTH_SHORT).show();
                //Titulo.setText(t.getMessage());
                return;
            }
        });
    }

    private void ValijasContador() {
        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        //String resul = mTvResult.getText().toString();
        ValijaContador valijaContador = new ValijaContador("145");
        Call<ValijaContador> call = jsonPlaceHolderApi.createPost(valijaContador);
        call.enqueue(new Callback<ValijaContador>() {
            @Override
            public void onResponse(Call<ValijaContador> call, Response<ValijaContador> response) {
                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ValijaContador postsResponse = response.body();

                    //String Estado = postsResponse.estado();
                    //String Guia = postsResponse.Guias();
                    Toast.makeText(getApplicationContext(), "API Contador Iniciado", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
                    Bundle c = new Bundle();
                    //c.putString("Estado", Estado);
                    //c.putString("Guia", Guia);
                    //i.putExtras(c);
                    //Mensaje.append(""+response.body());
                    //Mensaje.append(""+response.headers());
                    //startActivity(i);
                    //Titulo.append(""+postsResponse.estado());
                    return;
                }
                ValijaContador postsResponse = response.body();

                //String Estado = postsResponse.estado();
                //String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(), "API Contador Iniciado", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
                Bundle c = new Bundle();
                //c.putString("Estado", Estado);
                //c.putString("Guia", Guia);
                i.putExtras(c);
                //Mensaje.append(""+postsResponse.estado());
                //Mensaje.append(""+postsResponse.Guias());
                //startActivity(i);
                //Titulo.append(""+postsResponse.estado());
                return;
            }

            @Override
            public void onFailure(Call<ValijaContador> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                //Titulo.setText(t.getMessage());
                return;
            }
        });
    }



}
