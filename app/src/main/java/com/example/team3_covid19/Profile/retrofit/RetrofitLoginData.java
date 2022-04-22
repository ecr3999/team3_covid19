package com.example.team3_covid19.Profile.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitLoginData {

    private LoginService API;

    public RetrofitLoginData(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginService.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API = retrofit.create(LoginService.class);
    }

    public LoginService getAPI(){
        return API;
    }
}
