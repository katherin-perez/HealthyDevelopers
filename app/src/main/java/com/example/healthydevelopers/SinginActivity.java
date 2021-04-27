package com.example.healthydevelopers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SinginActivity extends AppCompatActivity {

    TextView txtName, txtLastName, txtAddress, txtMailSingin, txtPasswordSingin, txtPhoneNumber;
    Spinner spinnerSex;
    Button btnSinginUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);

        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);
        txtMailSingin = findViewById(R.id.txtMailSingin);
        txtPasswordSingin = findViewById(R.id.txtPasswordSingin);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtAddress = findViewById(R.id.txtAddress);
        spinnerSex = findViewById(R.id.spinnerSex);
        btnSinginUser = findViewById(R.id.btnSinginUser);

        String[] sex = {"Mujer","Hombre"};
        //Crear objeto para comunicar el codigo con el spinner del archico de diseño
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex);
        spinnerSex.setAdapter(adapter);

    }


}