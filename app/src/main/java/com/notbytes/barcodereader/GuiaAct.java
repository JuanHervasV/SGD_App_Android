package com.notbytes.barcodereader;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;
import com.notbytes.barcode_reader.BarcodeReaderFragment;
import com.notbytes.barcodereader.Model.ValidarGuia;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuiaAct extends AppCompatActivity implements BarcodeReaderFragment.BarcodeReaderListener {
    private TextView Manifiesto;
    private TextView Valija;
    private EditText Guia;
    private APIRetrofitInterface jsonPlaceHolderApi;
    private TextView Mensaje;
    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;
    private Button Agregar;
    private Button Cerrar;
    private FrameLayout Contenedor;
    private ListView listView;
    private TextView contarelementos;
    int totalelementoslist = 1;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia);

        //Lista--------------------------------------------------------
        listView= findViewById(R.id.listview);
        String[]android_flavours={"Hey","Hola"};
        arrayList= new ArrayList<>(Arrays.asList(android_flavours));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        // Result value = barcode.receiveScan();

        //arrayList.add(barcode.rawValue);
        for (int i = 0; i < android_flavours.length; i++) {
            arrayList.add(android_flavours[i]);

            HashSet<String> hashSet = new HashSet<String>();
            hashSet.addAll(arrayList);
            arrayList.clear();
            arrayList.addAll(hashSet);
        }

        //listarpro = findViewById(R.id.lista);
        //String resul = mTvResult.getText().toString();----------------

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

        Agregar = findViewById(R.id.btnAgregar);
        Cerrar = findViewById(R.id.btnCerrar);

        Agregar.setOnTouchListener(new View.OnTouchListener() {
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

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAgregar:
                createPost();
                break;
            case R.id.btnBr:
                Guia = findViewById(R.id.txtGuia);
                Guia.setVisibility(View.INVISIBLE);
                Contenedor = findViewById(R.id.fm_container);
                Contenedor.setVisibility(View.VISIBLE);
                addBarcodeReaderFragment();
                break;
                /*FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                Fragment fragmentById = supportFragmentManager.findFragmentById(R.id.fm_container);
                if (fragmentById != null) {
                    fragmentTransaction.remove(fragmentById);
                }
                fragmentTransaction.commitAllowingStateLoss();
                launchBarCodeActivity();*/
            case R.id.btnCerrar:
                PasarDatos();
                break;
            case R.id.btnTipear:
                Guia = findViewById(R.id.txtGuia);
                Guia.setVisibility(View.VISIBLE);
                Contenedor = findViewById(R.id.fm_container);
                Contenedor.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void addBarcodeReaderFragment() {
        BarcodeReaderFragment readerFragment = BarcodeReaderFragment.newInstance(true, false, View.VISIBLE);
        readerFragment.setListener(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fm_container, readerFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void onScanned(Barcode barcode) {

        Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();
        //mTvResultHeader.setText("Datos generales");
     /*   //Lista---------------------------------------------------------
        listView= findViewById(R.id.listview);
        String[]android_flavours={};
        arrayList= new ArrayList<>(Arrays.asList(android_flavours));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        // Result value = barcode.receiveScan();


        //arrayList.add(barcode.rawValue);
        for (int i = 0; i < android_flavours.length; i++) {
            arrayList.add(android_flavours[i]);
            //Agregar datos a la list
        }
*/
        //Agregar datos a la list
        //Agrega datos al textview
        //mTvResult.setText("Último valor scaneado: "+barcode.rawValue+".");
        arrayList.add(barcode.rawValue);

        HashSet<String> hashSet = new HashSet<String>();
        hashSet.addAll(arrayList);
        arrayList.clear();
        arrayList.addAll(hashSet);

        //Contar elementos del spinner ~
        //int count = listView.getAdapter().getCount();
        //int ctf= totalelementoslist+count;
        //contarelementos.setText(""+count);
        //---

        //Sonido beep
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beeps);
        mp.start();
        //--

        adapter.notifyDataSetChanged();
    }


    public void onScannedMultiple(List<Barcode> barcodes) {

    }


    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }


    public void onScanError(String errorMessage) {

    }

    public void onCameraPermissionDenied() {
        Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_LONG).show();
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
        String ValijaID = b.getString("ValijaID");
        //----------------------------------------------------------------------
        String Gui = Guia.getText().toString();

        Manifiesto.setText(Mfto+" "+MftoAnio+" "+MftoNro+" "+Suc+" "+Gui);
        Valija.setText(Valijas);

        //Titulo = findViewById(R.id.txtTitulo);
        //Aqui enviar los datos
        //String resul = mTvResult.getText().toString();
        ValidarGuia validarGuia = new ValidarGuia("2014"/*+MftoAnio*/,"00000046"/*+MftoNro*/,"PEAQP01"/*+Suc*/,""+Gui);
        Call<ValidarGuia> call = jsonPlaceHolderApi.createPost(validarGuia);
        call.enqueue(new Callback<ValidarGuia>() {
            @Override
            public void onResponse(Call<ValidarGuia> call, Response<ValidarGuia> response) {
                if(!response.isSuccessful()){
                    //TestApi.setText("Codigo:" + response.code());
                    Toast.makeText(getApplicationContext(),"Algo está mal ~",Toast.LENGTH_SHORT).show();
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
                //Mensaje.append(""+postsResponse.estado());
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
            //Sonido wrong
            final MediaPlayer mp2 = MediaPlayer.create(this,R.raw.wrong);
            mp2.start();
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
        String ValijaID = b.getString("ValijaID");
        //----------------------------------------------------------------------
        Manifiesto.setText(Mfto);
        Valija.setText(Valijas+"/"+ValijaID);
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
        String ValijaID = b.getString("ValijaID");
        //----------------------------------------------------------------------
        Manifiesto.setText(Mfto);
        Valija.setText(Valijas + "/" +ValijaID);
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
        c.putString("ValijaID", ValijaID);
        i.putExtras(c);
        startActivity(i);
        //----------------------------------------------------------------------
    }


}
