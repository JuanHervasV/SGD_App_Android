package com.notbytes.barcodereader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;
import com.notbytes.barcodereader.Model.ValijaAdicionar;
import com.notbytes.barcodereader.Model.ValijaValidar;
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
    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;
    private Button Asignar;
    private Button Cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valija);

        //Retrofit------------------------------------------------------

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiSGD/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        RecuperarDatos();
        onTouch();
        //createPost();
    }

    public void onTouch() {
        Asignar = findViewById(R.id.btnAsignar);
        Cerrar = findViewById(R.id.btnCerrar);

        Asignar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornerneutral);
                    //v.setBackgroundColor(Color.parseColor("#9C9C9C"));
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_corners);
                    //v.setBackgroundColor(Color.parseColor("#FF7177"));
                }
                return false;
            }
        });

        Cerrar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornerneutral);
                    //v.setBackgroundColor(Color.parseColor("#9C9C9C"));
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_corners);
                    //v.setBackgroundColor(Color.parseColor("#FF7177"));
                }
                return false;
            }
        });
    }

    private void RecuperarDatos(){

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

        Manifiesto.setText(Suc+" "+MftoAnio+"-"+MftoNro);
        Destino.setText(""+CiuDes);

        //

    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnAsignar:
                ValijaValidar();
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
        }
    }

    private void launchBarCodeActivity() {
        Intent launchIntent = BarcodeReaderActivity.getLaunchIntent(this, true, false);
        startActivityForResult(launchIntent, BARCODE_READER_ACTIVITY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Valija = findViewById(R.id.txtValija);

        if (requestCode == BARCODE_READER_ACTIVITY_REQUEST && data != null) {
            Barcode barcode = data.getParcelableExtra(BarcodeReaderActivity.KEY_CAPTURED_BARCODE);
            Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();

            //Agrega datos al textview
            //mTvResult.setText("Último valor scaneado: "+barcode.rawValue);
            //Agregar datos a la list

            //arrayList.add(barcode.rawValue);
            //adapter.notifyDataSetChanged();

            //HashSet<String> hashSet = new HashSet<String>();
            //hashSet.addAll(arrayList);
            //arrayList.clear();
            //arrayList.addAll(hashSet);
            //Contar elementos del spinner ~
            //int count = listView.getAdapter().getCount();
            //int ctf= totalelementoslist+count;
            //contarelementos.setText(""+count);
            //---

            //Sonido beep
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.beeps);
            mp.start();
            //--
            //mTvResultHeader.setText("Resultado");

            Valija.setText(barcode.rawValue);
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
                        //ValijaValidar();
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

    public void ValijaValidar(){
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

        //Aqui enviar los datos-------------------------------------------------------------------------------------------
        //String resul = mTvResult.getText().toString();
        ValijaValidar valijaValidar = new ValijaValidar(Valijas);
        Call<ValijaValidar> call = jsonPlaceHolderApi.createPost(valijaValidar);
        call.enqueue(new Callback<ValijaValidar>() {
            @Override
            public void onResponse(Call<ValijaValidar> call, Response<ValijaValidar> response) {
                if(!response.isSuccessful()){
                    //mJsonTxtView.setText("Codigo:" + response.code());
                    ValijaValidar postsResponse = response.body();

                    String Estado = postsResponse.Estado();
                    String Anio = postsResponse.Anio();
                    String Existe = postsResponse.Existe();
                    String Nro = postsResponse.Nro();
                    String Suc = postsResponse.Suc();
                    String ValijaID = postsResponse.ValijaID();

                    Toast.makeText(getApplicationContext(),"Validador ok",Toast.LENGTH_SHORT).show();
                    //Bundle c = new Bundle();
                    //c.putString("Estado", Estado);
                    //c.putString("Guia", Guia);
                    //i.putExtras(c);
                    //Mensaje.append(""+response.body());
                    //Mensaje.append(""+response.headers());
                    //startActivity(i);
                    //Titulo.append(""+postsResponse.estado());
                    return;
                }
                ValijaValidar postsResponse = response.body();

                String Estado = postsResponse.Estado();
                String Anio = postsResponse.Anio();
                String Existe = postsResponse.Existe();
                String Nro = postsResponse.Nro();
                String Suc = postsResponse.Suc();
                String ValijaID = postsResponse.ValijaID();

                //String Estado = postsResponse.estado();
                //String Guia = postsResponse.Guias();
                Toast.makeText(getApplicationContext(),"Contador Ok",Toast.LENGTH_SHORT).show();

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
                c.putString("ValijaID", ValijaID);
                i.putExtras(c);
                //Mensaje.append(""+postsResponse.estado());
                //Mensaje.append(""+postsResponse.Guias());
                //createPost();
                startActivity(i);
                //Titulo.append(""+postsResponse.estado());
                return;
            }
            @Override
            public void onFailure(Call<ValijaValidar> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fallo al ingresar los datos, compruebe su red.",Toast.LENGTH_SHORT).show();
                //Titulo.setText(t.getMessage());
                return;
            }
        });
    }

}
