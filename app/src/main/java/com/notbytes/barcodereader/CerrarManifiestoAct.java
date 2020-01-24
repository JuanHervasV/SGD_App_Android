package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.ManifiestoCerrar;
import com.notbytes.barcodereader.Model.ManifiestoContador;
import com.notbytes.barcodereader.Model.ManifiestoValija;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CerrarManifiestoAct extends AppCompatActivity {

    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<Integer> data1 = new ArrayList<Integer>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private TableLayout table;
    private TextView Manifiesto;

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
        ManifiestoValijas();
        RelletarTabla();
        ContadorManifiestos();

        //ManifiestoValijas();

    }

    public void RelletarTabla(){
        data.add("Valija");
        //data.add("1");
        //data1.add(1);
        //data1.add("2");
        data2.add("Cerrado");
        //data2.add("3");

        table = findViewById(R.id.tabla);
        //ManifiestoValijas();
        /*for(int i=0;i<data.size();i++)
        //{
            TableRow row=new TableRow(this);
            String TValija = data.get(i);
            //int TGuias = data1.get(i);
            String TCerrado = data2.get(i);
            TextView tv1=new TextView(this);
            tv1.setText(TValija);
            TextView tv2=new TextView(this);
            //tv2.setText(TGuias);
            TextView tv3=new TextView(this);
            tv3.setText(TCerrado);
            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            table.addView(row);
        }*/
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
        final TableRow row = new TableRow(this);
        final TextView tv1=new TextView(this);
        final TextView tv2=new TextView(this);
        final TextView tv3=new TextView(this);

        Manifiesto = findViewById(R.id.txtMfto);
        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        //String resul = mTvResult.getText().toString();
        ManifiestoValija manifiestoValija = new ManifiestoValija("00014000","PELIM01","2016");
        Call<ManifiestoValija> call = jsonPlaceHolderApi.createPost(manifiestoValija);
        call.enqueue(new Callback<ManifiestoValija>() {
            @Override
            public void onResponse(Call<ManifiestoValija> call, Response<ManifiestoValija> response) {
                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ManifiestoValija postsResponse = response.body();

                    String Valija = postsResponse.valija();
                    int Guia = postsResponse.total();
                    String Cerrado = postsResponse.cerrado();

                    /*  data.add(Valija);
                    data1.add(Guia);
                    data2.add(Cerrado);

                    table = findViewById(R.id.tabla);
                    //ManifiestoValijas();
                    for(int i=0;i<data.size();i++)
                    {
                        String TValija = data.get(i);
                        //int TGuias = data1.get(i);
                        String TCerrado = data2.get(i);
                        tv1.setText(TValija);
                        //tv2.setText(TGuias);
                        tv3.setText(TCerrado);
                        row.addView(tv1);
                        //row.addView(tv2);
                        row.addView(tv3);
                        table.addView(row);
                    }*/

                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
                    Bundle c = new Bundle();

                    Manifiesto.append(": "+Valija+" "+Guia+""+" "+Cerrado);

                    return;
                }
                ManifiestoValija postsResponse = response.body();

                String Valija = postsResponse.valija();
                int Guia = postsResponse.total();
                String Cerrado = postsResponse.cerrado();

                /*data.add(Valija);
                data1.add(Guia);
                data2.add(Cerrado);

                table = findViewById(R.id.tabla);
                //ManifiestoValijas();
                for(int i=0;i<data.size();i++){
                    String TValija = data.get(i);
                    //int TGuias = data1.get(i);
                    String TCerrado = data2.get(i);
                    tv1.setText(TValija);
                    //tv2.setText(TGuias);
                    tv3.setText(TCerrado);
                    row.addView(tv1);
                    //row.addView(tv2);
                    row.addView(tv3);
                    table.addView(row);
                }*/

                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
                Bundle c = new Bundle();
                //c.putString("Estado", Estado);
                //c.putString("Guia", Guia);
                i.putExtras(c);

                Manifiesto.append(": "+Valija+" "+Guia+""+" "+Cerrado);

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


