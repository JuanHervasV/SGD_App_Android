package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);
        RecuperarDatos();
        ValijasContador();
        onTouch();
    }

    public void onTouch() {

        CerrarValija = findViewById(R.id.btnCerrar);
        Volver = findViewById(R.id.btnVolver);

        CerrarValija.setOnTouchListener(new View.OnTouchListener() {
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

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCerrar:
                cerrarValija();
                cerrarStatus();
                break;
            case R.id.btnVolver:
                Intent i = new Intent(CerrarValijaAct.this, GuiaAct.class);
                startActivity(i);
        }
    }

    public void PasarDatos(){
        Manifiesto = findViewById(R.id.txtMfto);
        Valija = findViewById(R.id.txtValija);
        //Llamar datos ----------------------------------------------------------
        Bundle b = getIntent().getExtras();
        String Valijas = b.getString("Valijas");
        String Mfto = b.getString("Mfto");
        String MftoAnio = b.getString("MftoAnio");
        String MftoNro = b.getString("MftoNro");
        String Suc = b.getString("Suc");
        String PaisDes = b.getString("PaisDes");
        String CiuDes = b.getString("CiuDes");
        String Estado = b.getString("Estado");
        //----------------------------------------------------------------------
        Manifiesto.setText(Mfto);
        Valija.setText(Valijas);

        //Enviar datos----------------------------------------------------------
        Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
        //String passingdata = LoginText.getText().toString();
        Bundle c = new Bundle();
        c.putString("Valijas", Valijas);
        c.putString("Mfto", Mfto);
        c.putString("MftoAnio", MftoAnio);
        c.putString("MftoNro", MftoNro);
        c.putString("Suc", Suc);
        c.putString("PaisDes", PaisDes);
        c.putString("CiuDes", CiuDes);
        c.putString("Estado", Estado);
        i.putExtras(c);
        startActivity(i);
        //----------------------------------------------------------------------

    }

    public void RecuperarDatos(){
        //Llamar datos -------------------------------------------------
        Bundle b = getIntent().getExtras();
        String Mfto = b.getString("Mft");
        String Valijas = b.getString("Valijas");
        String MftoAnio = b.getString("MftoAnio");
        String MftoNro = b.getString("MftoNro");
        String Suc = b.getString("Suc");
        String PaisDes = b.getString("PaisDes");
        String CiuDes = b.getString("CiuDes");
        String Estado = b.getString("Estado");
        String ValijaID = b.getString("ValijaID");
        //---------------------------------------------------------------

        Manifiesto = findViewById(R.id.txtMfto);
        //Manifiesto.setText(Mfto);
        Manifiesto.setText("Manifiesto: "+Suc+" "+MftoAnio+"-"+MftoNro);
        Valija = findViewById(R.id.txtValija);
        Valija.append(""+Valijas+"?");
        Guia = findViewById(R.id.txtGuia);
        //String Guias = Guia.getText().toString();

        //Enviar datos---------------------------------------------------
        Intent i = new Intent(CerrarValijaAct.this, CerrarManifiestoAct.class);
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
        c.putString("ValijaID", ValijaID);
        i.putExtras(c);
        //startActivity(i);
        //----------------------------------------------------------------
    }

    private void cerrarValija(){
        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        //String resul = mTvResult.getText().toString();
        //Llamar datos -------------------------------------------------
        Bundle b = getIntent().getExtras();
        String Mfto = b.getString("Mft");
        String Valijas = b.getString("Valijas");
        String MftoAnio = b.getString("MftoAnio");
        String MftoNro = b.getString("MftoNro");
        String Suc = b.getString("Suc");
        String PaisDes = b.getString("PaisDes");
        String CiuDes = b.getString("CiuDes");
        String Estado = b.getString("Estado");
        String ValijaID = b.getString("ValijaID");
        //---------------------------------------------------------------
        ValijaCerrar valijaCerrar = new ValijaCerrar(ValijaID);
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
                    //startActivity(i);
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
                //startActivity(i);
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
                    //startActivity(i);
                    PasarDatos();
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
        Guia = findViewById(R.id.txtGuia);

        //Llamar datos -------------------------------------------------
        Bundle c = getIntent().getExtras();
        String ValijaID = c.getString("ValijaID");
        //---------------------------------------------------------------

        ValijaContador valijaContador = new ValijaContador("149");
        Call<ValijaContador> call = jsonPlaceHolderApi.createPost(valijaContador);
        call.enqueue(new Callback<ValijaContador>() {
            @Override
            public void onResponse(Call<ValijaContador> call, Response<ValijaContador> response) {
                if (!response.isSuccessful()) {
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ValijaContador postsResponse = response.body();

                    String Total = postsResponse.total();
                    String Valija = postsResponse.valija();

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
                    Guia.append(" "+Total+" //// "+Valija);
                    return;
                }
                ValijaContador postsResponse = response.body();

                String Total = postsResponse.total();
                String Valija = postsResponse.valija();

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
                Guia.append(" "+Total+" //// "+Valija);
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
