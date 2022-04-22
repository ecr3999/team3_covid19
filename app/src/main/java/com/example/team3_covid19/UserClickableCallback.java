package com.example.team3_covid19;

import android.view.View;

import com.example.team3_covid19.CovidMenu.retrofit.CovidData;

public interface UserClickableCallback {
    void onClick(View view, CovidData covidData);
}
