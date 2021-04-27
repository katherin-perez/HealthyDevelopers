package com.example.healthydevelopers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.MalformedInputException;

public class MainActivity extends AppCompatActivity {

    // iniciar sesión, registrarse
    Button btnLogin, btnSingIn;
    EditText txtMail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMail = findViewById(R.id.txtMailLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSingIn = findViewById(R.id.btnSingin);

        singinUser();
    }

    private void singinUser(){
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SinginActivity.class));
            }
        });
    }
}