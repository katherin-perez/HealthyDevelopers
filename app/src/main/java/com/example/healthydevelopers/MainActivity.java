package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.Message;
import com.example.healthydevelopers.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvMainTitle;
    Button btnSignin, btnLoginMain;
    EditText txtPasswordLogin, txtMailLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMailLogin = findViewById(R.id.txtMailLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        btnSignin = findViewById(R.id.btnSignin);
        btnLoginMain = findViewById(R.id.btnLoginMain);
        tvMainTitle =  findViewById(R.id.tvMainTitle);

        loginThatIs(txtMailLogin.getText().toString(), txtPasswordLogin.getText().toString());
        //finishLogin();
        //getUsers();
        //findUser();
        signinUser();


    }
    private void loginThatIs(String mail, String password){
        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit =  new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);
                Call<Message> call = healthyDevelopers.message(mail, password);

                /*call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if (!response.isSuccessful()){ //nos da un código de respuesta
                            tvMainTitle.setText("Codigo: "+response.code());
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "wui...", Toast.LENGTH_LONG).show();
                        Message message = response.body();
                        if (message.getMessage().equals("Correct")){
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error "+t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                 */
            }
        });

    }

    //evento de click para registrar un usuario nuevo
    private void signinUser(){
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SigninActivity.class));
            }
        });
    }
    //Para pedir toda la lista de usuarios
    private void finishLogin() {
        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofit();

            }
        });
    }
    private void retrofit(){
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://healthydevelopers.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Llamar a la interfaz
        HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

        Call<List<User>> call = healthyDevelopers.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()){ //nos da un código de respuesta
                    tvMainTitle.setText("Codigo: "+response.code());
                    return;
                }
                //respuesta del servidor
                List<User> userList = response.body();
                boolean aux = false;
                for (User user:userList) {
                    if(txtMailLogin.getText().toString().equals(user.getMail()) && txtPasswordLogin.getText().toString().equals(user.getPassword())){
                        //abrir activity
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        aux = true;
                        break;
                    }
                }
                if (!aux) {
                    Toast.makeText(getApplicationContext(), "Alguno de los datos está incorrecto", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                tvMainTitle.setText(t.getMessage());
            }
        });
    }
    //para pedir un solo usuario a al base de datos
    private void findUser(){
        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findUserInfo(txtMailLogin.getText().toString());
            }
        });
    }

    private void findUserInfo(String mail){
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://healthydevelopers.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);
        Call<User> call = healthyDevelopers.find(mail);

        call.enqueue(new Callback<User>() {
            @Override //este se ejecuta si hay una respuesta del servidor
            public void onResponse(Call<User> call, Response<User> response) {
                try {
                    if (response.isSuccessful()){
                        User actual = response.body();
                        Log.i("Prueba ", "onResponse: "+response.body().getPassword());
                        if (txtPasswordLogin.getText().toString().equals(actual.getPassword())){
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Usuario no registrado", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override// este se ejecuta si hay problemas de conexión con el servidor
            public void onFailure(Call<User> call, Throwable t) {
                tvMainTitle.setText(t.getMessage());
            }
        });
    }

    //función base para trabajar el método GET
    private void getUsers(){
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://healthydevelopers.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

        Call<List<User>> call = healthyDevelopers.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()){
                    tvMainTitle.setText("Codigo: "+response.code());
                    return;
                }

                List<User> userList = response.body();

                for (User user : userList){
                    String content = "";
                    content += "ID: "+user.getId()+"\n";
                    content += "Nombre: "+user.getName()+"\n";
                    content += "Apellido: "+user.getLast_name()+"\n";
                    content += "Sexo: "+user.getSex()+"\n";
                    content += "Correo: "+user.getMail()+"\n";
                    content += "Contraseña: "+user.getPassword()+"\n";
                    content += "Telefono: "+user.getPhone()+"\n";
                    content += "Dirección: "+user.getDirection()+"\n";
                    tvMainTitle.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                tvMainTitle.setText(t.getMessage());
            }
        });
    }
}