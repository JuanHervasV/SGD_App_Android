package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {
    private Button Despachar;
    private Button Consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        onTouch();
    }

    public void onTouch() {

        Despachar = findViewById(R.id.btnDespachar);
        Consultar = findViewById(R.id.btnConsultar);
        Despachar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_corners);
                }
                return false;
            }
        });

        Consultar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_corners);
                }
                return false;
            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDespachar:
                //Intent i = new Intent(MenuPrincipal.this, MainActivity.class);
                RecuperarUsuarioDespachar();
                //startActivity(i);
                break;
            case R.id.btnConsultar:
                //Intent e = new Intent(MenuPrincipal.this, sinametm.class);
                RecuperarUsuarioConsultar();
                //startActivity(e);
                break;
        }

    }

    public void RecuperarUsuarioDespachar(){
        //String passingdata = LoginText.getText().toString();
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        Spinner mySpinner = findViewById(R.id.estado);
        //final String Estado = mySpinner.getSelectedItem().toString();

        //Llamar datos usuario--------------------------
        Bundle c = getIntent().getExtras();
        String receivingdata = c.getString("Key");
        //String usu = receivingdata.toString();
        //TextView tv = findViewById(R.id.usuarios);
        //tv.append(receivingdata);
        //----------------------------------------------
        String passingdata="Hola";
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        Intent i = new Intent(MenuPrincipal.this, ManifiestoAct.class);
        //Intent a = new Intent(Logeo.this, Pre_estado.class);
        Bundle b = new Bundle();
        b.putString("Key", receivingdata);
        //b.putString("Key2", Estado);
        i.putExtras(b);
        startActivity(i);
    }

    public void RecuperarUsuarioConsultar(){
        //String passingdata = LoginText.getText().toString();
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        Spinner mySpinner = findViewById(R.id.estado);
        //final String Estado = mySpinner.getSelectedItem().toString();

        //Llamar datos usuario--------------------------
        Bundle c = getIntent().getExtras();
        String receivingdata = c.getString("Key");
        //String usu = receivingdata.toString();
        //TextView tv = findViewById(R.id.usuarios);
        //tv.append(receivingdata);
        //----------------------------------------------
        String passingdata="Hola";
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        Intent i = new Intent(MenuPrincipal.this, MainActivity.class);
        //Intent a = new Intent(Logeo.this, Pre_estado.class);
        Bundle b = new Bundle();
        b.putString("Key", receivingdata);
        //b.putString("Key2", Estado);
        i.putExtras(b);
        startActivity(i);
    }

}
