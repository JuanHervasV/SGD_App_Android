package com.notbytes.barcodereader;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Logeo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login_login:
                Intent i = new Intent(Logeo.this, MainActivity.class);
                startActivity(i);
                break;

        }
    }
}
