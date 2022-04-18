package com.example.team3_covid19;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface DataCovidService {
    String BASE_URL = "https://corona.lmao.ninja";

    @GET("v2/countries/")
    Call<List<CovidData>> getCovidData();

    @GET("v2/countries/{country}")
    Call<CovidData> getCovidData(@Path("country") String country);
}
