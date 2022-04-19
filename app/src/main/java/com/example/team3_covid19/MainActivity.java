package com.example.team3_covid19;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<CovidData> covidData;
    private static final int NUMBER_OF_THREADS = 1;
    private Executor poolWorker = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitCovidData retrofitCovidData = new RetrofitCovidData();
        retrofitCovidData.getAPI().getCovidData().enqueue(new Callback<List<CovidData>>() {
            @Override
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                covidData = response.body();
                startThread();
            }

            @Override
            public void onFailure(Call<List<CovidData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("Failure:", t.getMessage());
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CovidListFragment.newInstance())
                    .commitNow();
        }
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Intent intent= new Intent(MainActivity.this, TestDbActivity.class);
            startActivity(intent);
        }
    }*/

    @Override
    protected void onResume() {
        boolean isAllow = SessionManagement.getInstance().isSessionActive(this, Calendar.getInstance().getTime());
        if(!isAllow){
            openLoginActivity();
        }
        super.onResume();
    }

    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void startThread(){
        poolWorker.execute(insertData);
    }

    private Thread insertData = new Thread(){
        @Override
        public void run(){
            super.run();
            CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
            CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    DataDao dataDao = database.dataDao();
                    for(int i = 0;i<covidData.size();i++){
                        Data data = new Data();
                        data.country = covidData.get(i).getCountry();
                        data.cases = covidData.get(i).getCases();
                        dataDao.insert(data);
                        Log.d("DataInserted", data.country);
                    }
                }
            });
        }
    };

}