package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

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
