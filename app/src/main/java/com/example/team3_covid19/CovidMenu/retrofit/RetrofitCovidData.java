package com.example.team3_covid19.CovidMenu.retrofit;

import com.example.team3_covid19.CovidMenu.retrofit.DataCovidService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCovidData {

    private DataCovidService API;

    public RetrofitCovidData(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DataCovidService.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API = retrofit.create(DataCovidService.class);
    }

    public DataCovidService getAPI(){
        return API;
    }
}
