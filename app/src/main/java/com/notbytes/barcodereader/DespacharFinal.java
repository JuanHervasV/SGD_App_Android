package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DespacharFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despachar_final);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVolver:
                Intent i = new Intent(DespacharFinal.this, MenuPrincipal.class);
                startActivity(i);
                break;
        }
    }
}