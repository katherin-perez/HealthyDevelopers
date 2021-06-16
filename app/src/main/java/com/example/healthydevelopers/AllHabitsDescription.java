package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.Habit;
import com.example.healthydevelopers.model.Progress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllHabitsDescription extends AppCompatActivity {

    TextView tvHeaderDescription, tvDescription;
    ListView lstAdvices;
    Button btnImplement;
    public static String separador = "»";
    public static int indice;
    public static Habit habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habits_description);

        tvHeaderDescription = findViewById(R.id.tvHeaderDescription);
        tvDescription = findViewById(R.id.tvDescription);
        lstAdvices = findViewById(R.id.lstAdvices);
        btnImplement = findViewById(R.id.btnImplement);

        indice = getIntent().getIntExtra("Indice", 123);

        habit = (Habit) AllHabitsList.habits.get(indice);
        String[] info = habit.getDescription().split(separador);
        tvHeaderDescription.setText(habit.getTitle());
        tvDescription.setText(info[0]);
        AdvicesAdapater adapater = new AdvicesAdapater(info, getApplicationContext());
        lstAdvices.setAdapter(adapater);

        if (UserHabitsList.progressHabit.getStatus() < 30){
            btnImplement.setVisibility(View.INVISIBLE);
        }
        addNewProgress();
    }

    public void addNewProgress() {
        btnImplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

                Progress progress = new Progress(1, habit.getId(), 0, MainActivity.current.getMail());

                Call<Progress> call = healthyDevelopers.addNewProgress(progress);
                call.enqueue(new Callback<Progress>() {
                    @Override
                    public void onResponse(Call<Progress> call, Response<Progress> response) {
                        Toast.makeText(getApplicationContext(), "¡Ánimo! faltan 30 días para finalizar con la implementación de tu nuevo hábito", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<Progress> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }
}