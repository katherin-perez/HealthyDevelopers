package com.example.healthydevelopers.interfaz;

import com.example.healthydevelopers.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HealthyDevelopers {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{mail}")
    Call<User> find(@Query("mail") String mail);

    @POST("users/new")
    Call<User> createUser(@Body User user);

}
