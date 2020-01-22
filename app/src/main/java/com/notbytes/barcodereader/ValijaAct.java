package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.ValijaAdicionar;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ValijaAct extends AppCompatActivity {
    private APIRetrofitInterface jsonPlaceHolderApi;
    private TextView Manifiesto;
    private TextView Destino;
    private EditText Valija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valija);

        Manifiesto = findViewById(R.id.txtMfto);
        Destino = findViewById(R.id.txtDestino);

        //Llamar datos --------------------------------
        Bundle b = getIntent().getExtras();
        String Mfto = b.getString("Mft");
        String MftoAnio = b.getString("MftoAnio");
        String MftoNro = b.getString("MftoNro");
        String Suc = b.getString("Suc");
        String PaisDes = b.getString("PaisDes");
        String CiuDes = b.getString("CiuDes");
        String Estado = b.getString("Estado");
        //----------------------------------------------

        Manifiesto.setText(Suc+""+MftoAnio+"-"+MftoNro);
        Destino.setText(PaisDes+""+CiuDes);

        //Retrofit------------------------------------------------------

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        //createPost();

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnAsignar:
                createPost();
                break;
        }
    }

    public void createPost(){

        Valija = findViewById(R.id.txtValija);
        final String Valijas = Valija.getText().toString();
        //Llamar datos ---------------------------------------
        Bundle b = getIntent().getExtras();
        final String Mfto = b.getString("Mft");
        final String MftoAnio = b.getString("MftoAnio");
        final String MftoNro = b.getString("MftoNro");
        final String Suc = b.getString("Suc");
        final String PaisDes = b.getString("PaisDes");
        final String CiuDes = b.getString("CiuDes");
        final String Estado = b.getString("Estado");
        //----------------------------------------------------

            ValijaAdicionar valijaAdicionar = new ValijaAdicionar(""+Valijas,""+MftoAnio,""+MftoNro,""+Suc,""+CiuDes,"pruebaUsuario");

            Call<ValijaAdicionar> call = jsonPlaceHolderApi.createPost(valijaAdicionar);
            call.enqueue(new Callback<ValijaAdicionar>() {
                @Override
                public void onResponse(Call<ValijaAdicionar> call, Response<ValijaAdicionar> response) {
                    if(!response.isSuccessful()){
                        //TestApi.setText("Codigo:" + response.code());
                        return;
                    }
                    ValijaAdicionar postsResponse = response.body();
                    String content = "";
                    content += "Estado:" + postsResponse.Estado() + "\n";
                    content += "Mensaje:" + postsResponse.Mensaje() + "\n";
                    //TestApi.append(content);
                    if(postsResponse.Estado() == "true") {
                        Toast.makeText(ValijaAct.this, "Valija asignada correctamente", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ValijaAct.this, GuiaAct.class);
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
                    }
                    else{
                        Toast.makeText(ValijaAct.this, "Fallo al asignar valjia", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ValijaAdicionar> call, Throwable t) {
                    Toast.makeText(ValijaAct.this, "Fallo al asignar valjia", Toast.LENGTH_SHORT).show();
                }
            });

    }

}
