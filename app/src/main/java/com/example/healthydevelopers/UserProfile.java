package com.example.healthydevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.interfaz.HealthyDevelopers;
import com.example.healthydevelopers.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfile extends AppCompatActivity {

    TextView tvNameProfile, tvLastNameProfile, tvMailProfile, tvSexProfile, tvPhoneNumberProfile, tvAddressProfile;
    ImageView imgUserProfile;
    String gender;
    Button btnDeleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvNameProfile = findViewById(R.id.tvNameProfile);
        tvLastNameProfile = findViewById(R.id.tvLastNameProfile);
        tvMailProfile = findViewById(R.id.tvMailProfile);
        tvSexProfile = findViewById(R.id.tvSexProfile);
        tvPhoneNumberProfile = findViewById(R.id.tvPhoneNumberProfile);
        tvAddressProfile = findViewById(R.id.tvAddressProfile);
        imgUserProfile = findViewById(R.id.imgUserProfile);
        btnDeleteUser = findViewById(R.id.btnDeleteUser);

        showUser();
        deleteUser();
    }

    private void deleteUser() {
        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://healthydevelopers.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                HealthyDevelopers healthyDevelopers = retrofit.create(HealthyDevelopers.class);

                User user = MainActivity.current;

                Call<User> call = healthyDevelopers.deleteUser(user.getMail());

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(getApplicationContext(), "Proceso exitoso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error " + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.i("Prueba ", "onResponse: " + t.getMessage());
                    }
                });
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void showUser() {
        if (MainActivity.current.getSex() == 'F'){
            imgUserProfile.setImageResource(R.drawable.woman_profile);
        }else {
            imgUserProfile.setImageResource(R.drawable.man_profile);
        }
        gender = MainActivity.current.getSex() == 'M' ? "Hombre" : "Mujer";
        tvNameProfile.setText((String)MainActivity.current.getName());
        tvLastNameProfile.setText((String)MainActivity.current.getLast_name());
        tvMailProfile.setText((String)MainActivity.current.getMail());
        tvSexProfile.setText(gender);
        tvPhoneNumberProfile.setText((String)MainActivity.current.getPhone());
        tvAddressProfile.setText((String)MainActivity.current.getDirection());
    }
}