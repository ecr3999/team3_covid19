package com.example.team3_covid19;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private DataCovidService API;

    public RetrofitInstance(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DataCovidService.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        retrofit.create(DataCovidService.class);
        API = retrofit.create(DataCovidService.class);
    }

    public DataCovidService getAPI(){
        return API;
    }
}
