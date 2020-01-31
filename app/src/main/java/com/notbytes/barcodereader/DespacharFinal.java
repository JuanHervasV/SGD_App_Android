package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DespacharFinal extends AppCompatActivity {
    private Button Volver;

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

    public void onTouch() {

        Volver = findViewById(R.id.btnVolver);
        Volver.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_corners);
                    //v.setBackgroundColor(Color.parseColor("@drawable/rounded_corners"));
                }
                return false;
            }
        });
    }


}