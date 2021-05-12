package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class HabitsList extends AppCompatActivity {

    ListView lstHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_list);

        lstHabits = findViewById(R.id.lstHabits);



    }
}