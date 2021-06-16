package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.healthydevelopers.model.Habit;

import java.util.ArrayList;

public class AllHabitsList extends AppCompatActivity {

    ListView lstAllHabits;
    public static ArrayList<Object> habits = new ArrayList<>();
    public static ArrayList<String> categories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habits_list);

        lstAllHabits = findViewById(R.id.lstAllHabits);

        llenarHabitosMostrar();

        AllHabitsAdapter adapter = new AllHabitsAdapter(habits, this);
        lstAllHabits.setAdapter(adapter);

        lstAllHabits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AllHabitsDescription.class);
                intent.putExtra("Indice", position);
                startActivity(intent);
            }
        });
    }

    public static void llenarHabitosMostrar(){
        categories.add(UserHabitsList.habitList.get(0).getCategory());
        int indice = 0;
        for (Habit habito : UserHabitsList.habitList){
            if (!habito.getCategory().equals(categories.get(indice))) {
                categories.add(habito.getCategory());
                indice++;
            }
        }
        for (String categoria : categories){
            habits.add(categoria);
            for (Habit habito : UserHabitsList.habitList){
                if (habito.getCategory().equals(categoria)){
                    habits.add(habito);
                }
            }
        }
    }
}