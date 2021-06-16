package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.Progress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateProgress extends AppCompatActivity {

    Button btnYes, btnNo, btnDeleteProgressHabit, btnConfirm, btnCancel;
    ConstraintLayout cAnimationConfirmProgress;
    TextView tvConfirmationMessage, tvQuestionUpdate;
    private boolean option; //false=btnNo, true=btnSi
    private Progress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_progress);

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnDeleteProgressHabit = findViewById(R.id.btnDeleteProgressHabit);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
        cAnimationConfirmProgress = findViewById(R.id.cAnimationConfirmProgress);
        tvConfirmationMessage = findViewById(R.id.tvConfirmationMessage);
        tvQuestionUpdate = findViewById(R.id.tvQuestionUpdate);

        clickYes();
        clickNo();
        deleteProgressHabit();
    }
    private void deleteProgressHabit() {
        btnDeleteProgressHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

                Progress progress = new Progress(0, UserHabitsList.progressHabit.getHabitId(),
                        0, UserHabitsList.progressHabit.getUsermail());

                Call<Progress> call = healthyDevelopers.deleteProgressHabit(progress.getUsermail());

                call.enqueue(new Callback<Progress>() {
                    @Override
                    public void onResponse(Call<Progress> call, Response<Progress> response) {
                        Toast.makeText(getApplicationContext(), "Hábito en progreso eliminado", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<Progress> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("Prueba ", "onResponse: " + t.getMessage());
                    }
                });
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }
    private void clickNo() {
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

                progress = new Progress(UserHabitsList.progressHabit.getHabitId(),
                        0, UserHabitsList.progressHabit.getUsermail());

                Call<Progress> call = healthyDevelopers.updateStatus(progress);
                call.enqueue(new Callback<Progress>() {
                    @Override
                    public void onResponse(Call<Progress> call, Response<Progress> response) {
                        cAnimationConfirmProgress.setVisibility(View.VISIBLE);
                        tvConfirmationMessage.setText((String) "Tu reto se reiniciará, pero recuerda ser sincero en todo momento");
                        option = false;
                        confirmAction();
                        cancelAction();
                        }

                    @Override
                    public void onFailure(Call<Progress> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("Prueba ", "onResponse: " + t.getMessage());
                    }
                });
            }
        });
    }
    private void clickYes() {
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

                progress = new Progress(UserHabitsList.progressHabit.getHabitId(),
                        UserHabitsList.progressHabit.getStatus() + 1, UserHabitsList.progressHabit.getUsermail());

                Call<Progress> call = healthyDevelopers.updateStatus(progress);
                call.enqueue(new Callback<Progress>() {
                    @Override
                    public void onResponse(Call<Progress> call, Response<Progress> response) {
                        cAnimationConfirmProgress.setVisibility(View.VISIBLE);
                        tvConfirmationMessage.setText((String) "Recuerda ser sincero contigo mismo, porque el beneficio final solo será para ti");
                        option = true;
                        confirmAction();
                        cancelAction();
                    }
                    @Override
                    public void onFailure(Call<Progress> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("Prueba ", "onResponse: " + t.getMessage());
                    }
                });
            }
        });
    }
    private void confirmAction(){
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                if (option){
                    //TODO: falta probar esta condición
                    if (progress.getStatus() == 30){
                        Toast.makeText(getApplicationContext(), "¡Felicidades! has implementado un nuevo hábito", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "¡Ánimo! faltan " + (30 - (UserHabitsList.progressHabit.getStatus() + 1)) +
                                " días para finalizar con la implementación de tu nuevo hábito", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "¡No te desanimes! afianza esa determinación y comienza de nuevo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void cancelAction(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cAnimationConfirmProgress.setVisibility(View.INVISIBLE);
            }
        });
    }
}