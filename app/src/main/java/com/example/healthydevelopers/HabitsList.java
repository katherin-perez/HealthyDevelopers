package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HabitsList extends AppCompatActivity {

    ListView lstHabits;
    ArrayList<Object> habits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_list);

        lstHabits = findViewById(R.id.lstHabits);

        habits.add("Alimenticios");
        habits.add(new Habit("", "Tomar mucha agua"));
        habits.add(new Habit("", "Consumir frutas"));
        habits.add("Laborales");
        habits.add(new Habit("", "Desconectar"));
        habits.add(new Habit("", "Planificar el tiempo"));
        habits.add("Físicos");
        habits.add(new Habit("", "Cuidado de la vista"));
        habits.add(new Habit("", "Postura corporal"));
        habits.add("Social/Familiar");
        habits.add(new Habit("", "Conversar con la familia"));
        habits.add(new Habit("", "Salir con amigos"));
        habits.add("Espiritual/Mental");
        habits.add(new Habit("", "Matenerse positivo"));
        habits.add(new Habit("", "Practicar el agradecimiento"));

        HabitsAdapter adapter = new HabitsAdapter(habits, this);
        lstHabits.setAdapter(adapter);

        lstHabits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AllHabitsDescription.class);
                intent.putExtra("HabitID", id);
                startActivity(intent);

            }
        });




    }
}