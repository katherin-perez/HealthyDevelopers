package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.model.User;

import java.util.ArrayList;

public class SigninActivity extends AppCompatActivity {

    TextView txtName, txtLastName, txtAddress, txtMailSingin, txtPasswordSingin, txtPhoneNumber;
    Spinner spinnerSex;
    Button btnSigninUser;
    ArrayList<User> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        txtName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtLastName);
        txtMailSingin = findViewById(R.id.txtMailSingin);
        txtPasswordSingin = findViewById(R.id.txtPasswordSingin);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtAddress = findViewById(R.id.txtAddress);
        spinnerSex = findViewById(R.id.spinnerSex);
        btnSigninUser = findViewById(R.id.btnSigninUser);

        String[] sex = {"Mujer","Hombre"};
        //Crear objeto para comunicar el codigo con el spinner del archico de diseño
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex);
        spinnerSex.setAdapter(adapter);

        createUser();
    }

    public void createUser() {
        btnSigninUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user;
                if (!txtMailSingin.getText().toString().equals("")  && !txtPasswordSingin.getText().toString().equals("") && !txtName.getText().toString().equals("")
                        && !txtLastName.getText().toString().equals("")) {
                    if (!txtAddress.getText().toString().equals("") && !txtPhoneNumber.getText().toString().equals("")) {
                        user = new User(1, txtName.getText().toString(), txtLastName.getText().toString(), ' ', txtMailSingin.getText().toString(),
                                txtPasswordSingin.getText().toString(), txtPhoneNumber.getText().toString(), txtAddress.getText().toString());
                    }else {
                        user = new User(1, txtName.getText().toString(), txtLastName.getText().toString(), ' ', txtMailSingin.getText().toString(),
                                txtPasswordSingin.getText().toString());
                    }
                    usersList.add(user);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Usuario Registrado Exitosamente", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Información incompleta", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}