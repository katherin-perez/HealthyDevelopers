package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvMainTitle;
    Button btnSignin, btnLoginMain;
    EditText txtPasswordLogin, txtMailLogin;
    ConstraintLayout cAnimationMain;
    public static User current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMailLogin = findViewById(R.id.txtMailLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        btnSignin = findViewById(R.id.btnSignin);
        btnLoginMain = findViewById(R.id.btnLoginMain);
        tvMainTitle = findViewById(R.id.tvMainTitle);
        cAnimationMain = findViewById(R.id.cAnimationMain);

        findUser();
        signinUser();
    }

    //evento de click para registrar un usuario nuevo
    private void signinUser() {
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SigninActivity.class));
            }
        });
    }

    //para pedir un solo usuario a al base de datos
    private void findUser() {
        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findUserInfo(txtMailLogin.getText().toString());
            }
        });
    }

    private void findUserInfo(String mail) {
        //mostrar ventanita cargando
        cAnimationMain.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthydevelopers.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);
        Call<User> call = healthyDevelopers.find(mail);

        call.enqueue(new Callback<User>() {
            @Override //este se ejecuta si hay una respuesta del servidor
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if (response.isSuccessful()) {
                        current = response.body();

                        //ocultar la ventanita de cargnado
                        cAnimationMain.setVisibility(View.GONE);

                        //Log.i("Prueba ", "onResponse: " + actual.getLast_name());
                        if (txtPasswordLogin.getText().toString().equals(current.getPassword())) {
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        findUserInfo(mail);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override// este se ejecuta si hay problemas de conexión con el servidor
            public void onFailure(Call<User> call, Throwable t) {
                findUserInfo(mail);
            }
        });
    }
}