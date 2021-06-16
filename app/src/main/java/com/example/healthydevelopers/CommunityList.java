package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityList extends AppCompatActivity {

    ListView lstCommunity;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_list);

        getUsersList();
    }

    //Para pedir toda la lista de usuarios
    private void getUsersList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthydevelopers.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Llamar a la interfaz
        HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

        Call<List<User>> call = healthyDevelopers.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) { //nos da un código de respuesta
                    getUsersList();
                    return;
                }
                //respuesta del servidor
                userList = response.body();
                lstCommunity = findViewById(R.id.lstCommunity);
                CommunityAdapter communityAdapter = new CommunityAdapter(userList, getApplicationContext());
                lstCommunity.setAdapter(communityAdapter);
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                getUsersList();
            }
        });
    }
}