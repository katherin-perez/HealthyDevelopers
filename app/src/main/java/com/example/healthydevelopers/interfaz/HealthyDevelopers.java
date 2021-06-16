package com.example.healthydevelopers.interfaz;

import android.content.Intent;

import com.example.healthydevelopers.model.Habit;
import com.example.healthydevelopers.model.Message;
import com.example.healthydevelopers.model.Progress;
import com.example.healthydevelopers.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HealthyDevelopers {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{mail}")
    Call<User> find(@Path("mail") String mail);

    @GET("habits")
    Call<List<Habit>> getHabit();

    @GET("progress/{mail}")
    Call<List<Progress>> getUserHabits(@Path("mail") String mail);

    @POST("users")
    Call<User> createUser(@Body User user);

    @POST("progress")
    Call<Progress> addNewProgress(@Body Progress progress);

    @PATCH("progress")
    Call<Progress> updateStatus(@Body Progress progress);

    @DELETE("progress/{mail}")
    Call<Progress> deleteProgressHabit(@Path("mail") String mail);

    @DELETE("users/{mail}")
    Call<User> deleteUser(@Path("mail") String mail);





}
