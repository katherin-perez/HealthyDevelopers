package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SigninActivity extends AppCompatActivity {

    TextView txtName, txtLastName, txtAddress, txtMailSingin, txtPasswordSingin, txtPhoneNumber;
    Spinner spinnerSex;
    Button btnSigninUser;
    String[] sex = {"Femenino", "Masculino"};
    private static char gender;

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

        //Crear objeto para comunicar el codigo con el spinner del archico de diseño
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex);
        spinnerSex.setAdapter(adapter);
        spinnerSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = spinnerSex.getSelectedItemPosition() == 0 ? 'M' : 'H';
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        createUser();
    }

    public void createUser() {
        btnSigninUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

                User user;
                if (!txtMailSingin.getText().toString().equals("") && !txtPasswordSingin.getText().toString().equals("") && !txtName.getText().toString().equals("")
                        && !txtLastName.getText().toString().equals("")) {
                    user = new User(0, txtName.getText().toString(), txtLastName.getText().toString(), gender, txtMailSingin.getText().toString(),
                            txtPasswordSingin.getText().toString(), txtPhoneNumber.getText().toString(), txtAddress.getText().toString());
                    Call<User> call = healthyDevelopers.createUser(user);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente"+response.body().getMail() , Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Información incompleta", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}