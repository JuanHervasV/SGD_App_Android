package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.ManifiestoCerrar;
import com.notbytes.barcodereader.Model.ManifiestoContador;
import com.notbytes.barcodereader.Model.ManifiestoValija;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CerrarManifiestoAct extends AppCompatActivity {

    private APIRetrofitInterface jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_manifiesto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        ContadorManifiestos();

        //ManifiestoValijas();

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnVolver:
                Intent i = new Intent(CerrarManifiestoAct.this, CerrarValijaAct.class);
                startActivity(i);
                break;
            case R.id.btnCerrar:
                CerrarManifiesto();
                break;
        }

    }

        private void CerrarManifiesto(){
            //Aqui enviar los datos-------------------------------------------------------------------------------------------
            //String resul = mTvResult.getText().toString();
                    ManifiestoCerrar manifiestoCerrar = new ManifiestoCerrar(1,"","","","","");
                    Call<ManifiestoCerrar> call = jsonPlaceHolderApi.createPost(manifiestoCerrar);
                    call.enqueue(new Callback<ManifiestoCerrar>() {
                @Override
                public void onResponse(Call<ManifiestoCerrar> call, Response<ManifiestoCerrar> response) {
                    if (!response.isSuccessful()) {
                        //mJsonTxtView.setText("Codigo:" + response.code());
                        ManifiestoCerrar postsResponse = response.body();

                        //String Estado = postsResponse.estado();
                        //String Guia = postsResponse.Guias();
                        Toast.makeText(getApplicationContext(), "Hecho", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
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
                    ManifiestoCerrar postsResponse = response.body();

                    String estado = postsResponse.Estado();
                    String mensaje = postsResponse.Mensaje();
                    Toast.makeText(getApplicationContext(), "Hecho", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
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
                public void onFailure(Call<ManifiestoCerrar> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                    //Titulo.setText(t.getMessage());
                    return;
                }
            });
        }

    private void ContadorManifiestos(){
        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        //String resul = mTvResult.getText().toString();
        ManifiestoContador manifiestoContador = new ManifiestoContador("1","","");
        Call<ManifiestoContador> call = jsonPlaceHolderApi.createPost(manifiestoContador);
        call.enqueue(new Callback<ManifiestoContador>() {
            @Override
            public void onResponse(Call<ManifiestoContador> call, Response<ManifiestoContador> response) {
                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ManifiestoContador postsResponse = response.body();

                    //String Estado = postsResponse.estado();
                    //String Guia = postsResponse.Guias();
                    Toast.makeText(getApplicationContext(), "API Contador Manifiesto Iniciado", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
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
                ManifiestoContador postsResponse = response.body();

                //String Estado = postsResponse.estado();
                //String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(), "API Contador Manifiesto Iniciado", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
                Bundle c = new Bundle();
                //c.putString("Estado", Estado);
                //c.putString("Guia", Guia);
                //i.putExtras(c);
                //Mensaje.append(""+postsResponse.estado());
                //Mensaje.append(""+postsResponse.Guias());
                //startActivity(i);
                //Titulo.append(""+postsResponse.estado());
                return;
            }

            @Override
            public void onFailure(Call<ManifiestoContador> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                //Titulo.setText(t.getMessage());
                return;
            }
        });
    }

    private void ManifiestoValijas(){
        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        //String resul = mTvResult.getText().toString();
        ManifiestoValija manifiestoValija = new ManifiestoValija("00020944","PELIM01","2016");
        Call<ManifiestoValija> call = jsonPlaceHolderApi.createPost(manifiestoValija);
        call.enqueue(new Callback<ManifiestoValija>() {
            @Override
            public void onResponse(Call<ManifiestoValija> call, Response<ManifiestoValija> response) {
                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ManifiestoValija postsResponse = response.body();

                    //String Valija = postsResponse.valija();
                    //int Total = postsResponse.total();
                    //String Cerrado = postsResponse.cerrado();

                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
                    Bundle c = new Bundle();
                    //c.putString("Valija", Valija);
                    //c.putInt("Total", Total);
                    //c.putString("Cerrado", Cerrado);
                    //i.putExtras(c);
                    //Mensaje.append(""+response.body());
                    //Mensaje.append(""+response.headers());
                    startActivity(i);
                    //Titulo.append(""+postsResponse.estado());
                    return;
                }
                ManifiestoValija postsResponse = response.body();

                //String Estado = postsResponse.estado();
                //String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
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
            public void onFailure(Call<ManifiestoValija> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                //Titulo.setText(t.getMessage());
                return;
            }
        });
    }
    }


