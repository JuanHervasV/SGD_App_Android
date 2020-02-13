package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.ManifiestoCerrar;
import com.notbytes.barcodereader.Model.ManifiestoContador;
import com.notbytes.barcodereader.Model.MftoValijas;
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
    private TextView Valija;
    private TextView Guia;
    private Button CerrarMfto;
    private Button Volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_manifiesto);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);
        RecuperarDatos();
        //ManifiestoValija();
        onTouch();
    }

    public void onTouch() {
        CerrarMfto = findViewById(R.id.btnCerrar);
        Volver = findViewById(R.id.btnVolver);

        CerrarMfto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornerneutral);
                    //v.setBackgroundColor(Color.parseColor("#9C9C9C"));
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                    //v.setBackgroundColor(Color.parseColor("#FF7177"));
                }
                return false;
            }
        });

        Volver.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornerneutral);
                    //v.setBackgroundColor(Color.parseColor("#9C9C9C"));
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                    //v.setBackgroundColor(Color.parseColor("#FF7177"));
                }
                return false;
            }
        });
    }

    public void RecuperarDatos(){
        //Llamar datos --------------------------------------------------
        Bundle b = getIntent().getExtras();
        String Mfto = b.getString("Mfto");
        String Valijas = b.getString("Valijas");
        String MftoAnio = b.getString("MftoAnio");
        String MftoNro = b.getString("MftoNro");
        String Suc = b.getString("Suc");
        String PaisDes = b.getString("PaisDes");
        String CiuDes = b.getString("CiuDes");
        String Estado = b.getString("Estado");
        //---------------------------------------------------------------

        Manifiesto = findViewById(R.id.txtMfto);
        //Manifiesto.setText(Mfto);
        Manifiesto.setText("¿Desea cerrar el manifiesto "+Mfto);
        /*Valija = findViewById(R.id.txtValija);
        Valija.append(""+Valijas+"?");
        Guia = findViewById(R.id.txtGuia);*/
        //String Guias = Guia.getText().toString();

        //Enviar datos---------------------------------------------------
        Intent i = new Intent(CerrarManifiestoAct.this, MenuPrincipal.class);
        //String passingdata = LoginText.getText().toString();
        Bundle c = new Bundle();
        c.putString("Valijas", Estado);
        c.putString("Mfto", Mfto);
        c.putString("MftoAnio", MftoAnio);
        c.putString("MftoNro", MftoNro);
        c.putString("Suc", Suc);
        c.putString("PaisDes", PaisDes);
        c.putString("CiuDes", CiuDes);
        c.putString("Estado", Estado);
        i.putExtras(c);
        //startActivity(i);
        //----------------------------------------------------------------
    }

    public void RelletarTabla(){
        data.add("Valija");
        data2.add("Cerrado");
        table = findViewById(R.id.tabla);

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnVolver:
                ValijasMftos();
                //ProcesarDatos();
                //Intent i = new Intent(CerrarManifiestoAct.this, CerrarValijaAct.class);
                //startActivity(i);
                break;
            case R.id.btnCerrar:
                CerrarManifiesto();
                break;
        }

    }

        private void CerrarManifiesto(){

        //Llamar datos --------------------------------------------------
            Bundle b = getIntent().getExtras();
            final String Mfto = b.getString("Mfto");
            String Valijas = b.getString("Valijas");
            final String MftoAnio = b.getString("MftoAnio");
            final String MftoNro = b.getString("MftoNro");
            final String Suc = b.getString("Suc");
            final String PaisDes = b.getString("PaisDes");
            final String CiuDes = b.getString("CiuDes");
            final String Estado = b.getString("Estado");
        //---------------------------------------------------------------

        //Aqui enviar los datos-------------------------------------------------------------------------------------------
            //String resul = mTvResult.getText().toString();
                    ManifiestoCerrar manifiestoCerrar = new ManifiestoCerrar(""+MftoNro,""+Suc,""+MftoAnio,"pruebaUsuario","3619");
                    Call<ManifiestoCerrar> call = jsonPlaceHolderApi.createPost(manifiestoCerrar);
                    call.enqueue(new Callback<ManifiestoCerrar>() {
                @Override
                public void onResponse(Call<ManifiestoCerrar> call, Response<ManifiestoCerrar> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Ha ocurrido un error con el manifiesto.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ManifiestoCerrar postsResponse = response.body();

                    String estado = postsResponse.Estado();
                    String mensaje = postsResponse.Mensaje();
                    Toast.makeText(getApplicationContext(), "Manifiesto cerrado correctamente.", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
                    Bundle c = new Bundle();
                    //c.putString("Estado", Estado);
                    //Enviar datos---------------------------------------------------
                    c.putString("Valijas", Estado);
                    c.putString("Mfto", Mfto);
                    c.putString("MftoAnio", MftoAnio);
                    c.putString("MftoNro", MftoNro);
                    c.putString("Suc", Suc);
                    c.putString("PaisDes", PaisDes);
                    c.putString("CiuDes", CiuDes);
                    c.putString("Estado", Estado);
                    i.putExtras(c);
                    startActivity(i);
                    //----------------------------------------------------------------
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

    /*private void ProcesarDatos(){
        //Llamar datos ---------------------------------------------------------------------------------------------------
        Bundle b = getIntent().getExtras();
        final String Mfto = b.getString("Mfto");
        String Valijas = b.getString("Valijas");
        final String MftoAnio = b.getString("MftoAnio");
        final String MftoNro = b.getString("MftoNro");
        final String Suc = b.getString("Suc");
        final String PaisDes = b.getString("PaisDes");
        final String CiuDes = b.getString("CiuDes");
        final String Estado = b.getString("Estado");
        //----------------------------------------------------------------------------------------------------------------
        Manifiesto = findViewById(R.id.txtMfto);
        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        MftoValijas mftoValijas = new MftoValijas(MftoNro,Suc,MftoAnio);
        Call<MftoValijas> call = jsonPlaceHolderApi.createPost(mftoValijas);
        call.enqueue(new Callback<MftoValijas>() {
            @Override
            public void onResponse(Call<MftoValijas> call, Response<MftoValijas> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Ocurrió un error en los datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                MftoValijas postsResponse = response.body();

                String Valija = postsResponse.Valija();
                int Guia = postsResponse.Total();
                String Cerrado = postsResponse.Cerrado();

                Toast.makeText(getApplicationContext(), "Recuento de valijas iniciado", Toast.LENGTH_SHORT).show();

                //Intent i = new Intent(CerrarManifiestoAct.this, DespacharFinal.class);
                //Bundle c = new Bundle();
                //i.putExtras(c);

                Manifiesto.append(": "+Valija+" "+Guia+""+" "+Cerrado);

                return;
            }

            @Override
            public void onFailure(Call<MftoValijas> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }*/

    private void ValijasMftos(){

        MftoValijas mftoValijas = new MftoValijas("00025111","PELIM01","2016");
        Call<MftoValijas> call = jsonPlaceHolderApi.createPostt(mftoValijas);
        call.enqueue(new Callback<MftoValijas>() {
            @Override
            public void onResponse(Call<MftoValijas> call, Response<MftoValijas> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"No correcto",Toast.LENGTH_SHORT).show();
                    return;
                }
                MftoValijas postsResponse = response.body();

                //String Mft_Ano = postsResponse.Valija();
                //int Mft_Nro = postsResponse.Total();
                //String Suc_Code = postsResponse.Cerrado();

                Toast.makeText(getApplicationContext(),"Pasó correctamente ",Toast.LENGTH_SHORT).show();
                return;
            }
            @Override
            public void onFailure(Call<MftoValijas> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fallo al ingresar los datos, compruebe su red.",Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
    }


