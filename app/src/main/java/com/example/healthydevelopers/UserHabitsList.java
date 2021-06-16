package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.Habit;
import com.example.healthydevelopers.model.Progress;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserHabitsList extends AppCompatActivity {

    public static List<Habit> habitList;
    public static List<Progress> currentList;
    public static Progress progressHabit;
    TextView tvNameHabitProgress, tvCategoryHabitProgress, tvPercentHabitProgress, tvTitleUserHabits2;
    ImageView imgUserHabitsList;
    ListView lstCompletedHabits;
    FloatingActionButton fabAllHabitsList;
    ConstraintLayout clHabitProgress, clFullHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_habits_list);
        tvNameHabitProgress = findViewById(R.id.tvNameHabitProgress);
        tvCategoryHabitProgress = findViewById(R.id.tvCategoryHabitProgress);
        tvPercentHabitProgress = findViewById(R.id.tvPercentHabitProgress);
        fabAllHabitsList = findViewById(R.id.fabAllHabitsList);
        lstCompletedHabits = findViewById(R.id.lstCompletedHabits);
        imgUserHabitsList = findViewById(R.id.imgUserHabitsList);
        tvTitleUserHabits2 = findViewById(R.id.tvTitleUserHabits2);
        clHabitProgress = findViewById(R.id.clHabitProgress);
        clFullHabit = findViewById(R.id.clFullHabit);

        getHabitsList();
    }
    //Obtener el listado de TODOS los hábitos
    private void getHabitsList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://healthydevelopers.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

        Call<List<Habit>> call = healthyDevelopers.getHabit();

        call.enqueue(new Callback<List<Habit>>() {
            @Override
            public void onResponse(Call<List<Habit>> call, Response<List<Habit>> response) {
                if (!response.isSuccessful()) {
                    getHabitsList();
                    return;
                }
                habitList = response.body();
                getUserHabitsList();
            }
            @Override
            public void onFailure(Call<List<Habit>> call, Throwable t) {
                getHabitsList();
            }
        });
    }
    private void getUserHabitsList() {
        currentList = HomeActivity.userProgressList;
        if (currentList.size() > 0) {
            progressHabit = currentList.get(currentList.size() - 1);
            if (progressHabit.getStatus() < 30) {
                tvCategoryHabitProgress.setVisibility(View.VISIBLE);
                tvPercentHabitProgress.setVisibility(View.VISIBLE);
                tvNameHabitProgress.setText((String) habitList.get(currentList.get(currentList.size() - 1).getHabitId() - 1).getTitle());
                tvCategoryHabitProgress.setText((String) habitList.get(currentList.get(currentList.size() - 1).getHabitId() - 1).getCategory());
                int percent = currentList.get(currentList.size() - 1).getStatus() * 100 / 30;
                tvPercentHabitProgress.setText(percent + "%");
                imgUserHabitsList.setImageResource(R.drawable.clock);
                currentList.remove(currentList.size() - 1);
            }
            if (currentList.size() > 0) {
                clFullHabit.setVisibility(View.VISIBLE);
                UserHabitsAdapter adapter = new UserHabitsAdapter(currentList, getApplicationContext());
                lstCompletedHabits.setAdapter(adapter);
            }
        }
        statusUpdate();
        openAllHabitsList();
    }
    private void statusUpdate() {
        clHabitProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UpdateProgress.class));
            }
        });
    }
    private void openAllHabitsList() {
        fabAllHabitsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllHabitsList.class));
            }
        });
    }
}