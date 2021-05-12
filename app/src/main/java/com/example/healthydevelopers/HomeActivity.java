package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btnHabits, btnCommunity, btnTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHabits = findViewById(R.id.btnHabits);
        btnTask = findViewById(R.id.btnTask);
        btnCommunity = findViewById(R.id.btnCommunity);

        seeHabitsList();
    }

    private void seeHabitsList(){
        btnHabits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HabitsList.class));
            }
        });
    }
}