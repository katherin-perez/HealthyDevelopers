package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.Progress;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    Button btnHabits, btnCommunity, btnTask;
    FloatingActionButton fabProfileHome;
    ConstraintLayout cAnimationUserList;
    public static List<Progress> userProgressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHabits = findViewById(R.id.btnHabits);
        btnTask = findViewById(R.id.btnTask);
        btnCommunity = findViewById(R.id.btnCommunity);
        fabProfileHome = findViewById(R.id.fabProfileHome);
        cAnimationUserList = findViewById(R.id.cAnimationHome);

        openHabitsList();
        openCommunityList();
        openUserProfile();
    }
    private void openUserProfile(){
        fabProfileHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        });
    }
    private void openHabitsList(){
        btnHabits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAnimationUserList.setVisibility(View.VISIBLE);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

                Call<List<Progress>> call = healthyDevelopers.getUserHabits(MainActivity.current.getMail());

                call.enqueue(new Callback<List<Progress>>() {
                    @Override
                    public void onResponse(Call<List<Progress>> call, Response<List<Progress>> response) {
                        if (!response.isSuccessful()) {
                            openHabitsList();
                            return;
                        }
                        cAnimationUserList.setVisibility(View.GONE);
                        userProgressList = response.body();
                        startActivity(new Intent(getApplicationContext(), UserHabitsList.class));
                    }
                    @Override
                    public void onFailure(Call<List<Progress>> call, Throwable t) {
                        openHabitsList();
                    }
                });
            }
        });
    }
    private void openCommunityList(){
        btnCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CommunityList.class));
            }
        });
    }
}