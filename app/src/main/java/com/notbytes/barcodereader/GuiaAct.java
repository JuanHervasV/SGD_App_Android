package com.notbytes.barcodereader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;
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
    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        RecuperarDatos();
        //createPost();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar:
                createPost();
                break;
            case R.id.btnBr:
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                Fragment fragmentById = supportFragmentManager.findFragmentById(R.id.fm_container);
                if (fragmentById != null) {
                    fragmentTransaction.remove(fragmentById);
                }
                fragmentTransaction.commitAllowingStateLoss();
                launchBarCodeActivity();
                break;
            case R.id.btnCerrar:
                PasarDatos();
                break;
        }
    }
    public void AgregarGuia(){

    }

    private void createPost(){
        Guia = findViewById(R.id.txtGuia);
        Mensaje = findViewById(R.id.txtMensaje);

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
        String Gui = Guia.getText().toString();

        Manifiesto.setText(Mfto+" "+MftoAnio+" "+MftoNro+" "+Suc+" "+Gui);
        Valija.setText(Valijas);

        //Titulo = findViewById(R.id.txtTitulo);
        //Aqui enviar los datos
        //String resul = mTvResult.getText().toString();
        ValidarGuia validarGuia = new ValidarGuia(""+MftoAnio,""+MftoNro,""+Suc,""+Gui);
        Call<ValidarGuia> call = jsonPlaceHolderApi.createPost(validarGuia);
        call.enqueue(new Callback<ValidarGuia>() {
            @Override
            public void onResponse(Call<ValidarGuia> call, Response<ValidarGuia> response) {
                if(!response.isSuccessful()){
                    //TestApi.setText("Codigo:" + response.code());
                    Toast.makeText(getApplicationContext(),"Something's wrong ~",Toast.LENGTH_SHORT).show();
                    return;
                }
                ValidarGuia postsResponse = response.body();

                String Estado = postsResponse.estado();
                String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(),"Dato ingresado exitosamente",Toast.LENGTH_SHORT).show();

                //Intent i = new Intent(GuiaAct.this, CerrarValijaAct.class);
                //    Bundle c = new Bundle();
                //c.putString("Estado", Estado);
                //c.putString("Guia", Guia);

                //i.putExtras(c);
                Mensaje.append(""+postsResponse.estado());
                Mensaje.append(""+postsResponse.Guias());
                //PasarDatos();
                //startActivity(i);
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

    private void launchBarCodeActivity() {
        Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(this, true, false);
        startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Guia = findViewById(R.id.txtGuia);

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();

            //Sonido beep
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.beeps);
            mp.start();
            //--
            //mTvResultHeader.setText("Resultado");

            Guia.setText(barcode.rawValue);
        }
    }

    public void RecuperarDatos(){
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
        Intent i = new Intent(GuiaAct.this, CerrarValijaAct.class);
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


}
