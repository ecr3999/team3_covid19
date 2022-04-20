package com.example.team3_covid19;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.team3_covid19.room.CovidDatabase;
import com.example.team3_covid19.room.CovidViewModel;
import com.example.team3_covid19.room.Data;
import com.example.team3_covid19.room.DataDao;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<CovidData> covidData;
    public CovidViewModel covidViewModel;
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
        covidViewModel = new ViewModelProvider(MainActivity.this).get(CovidViewModel.class);

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
        //poolWorker.execute(deleteAll);
        poolWorker.execute(insertData);
    }

    private Thread insertData = new Thread(){
        @Override
        public void run(){
            super.run();
            //CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
            //CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                //@Override
                //public void run() {
                    //DataDao dataDao = database.dataDao();
                    for(int i = 0;i<covidData.size();i++){
                        Data data = new Data();
                        data.country = covidData.get(i).getCountry();
                        data.continent = covidData.get(i).getContinent();
                        data.cases = covidData.get(i).getCases();
                        data.todayCases = covidData.get(i).getTodayCases();
                        data.deaths = covidData.get(i).getDeaths();
                        data.todayDeaths = covidData.get(i).getTodayDeaths();
                        data.recovered = covidData.get(i).getRecovered();
                        data.todayRecovered = covidData.get(i).getTodayRecovered();
                        //dataDao.insert(data);
                        covidViewModel.insert(data);
                        Log.d("DataInserted1", data.toString());
                    }
                    //List<Data> datas = dataDao.getAll();
                    LiveData<List<Data>> datas = covidViewModel.getAllDatas();
                    //Log.d("GetData", "DataInsertSuccess, Size : " +datas.getValue().size());
                    //Log.d("GetData", "Data, Detail : " + String.valueOf(datas.getValue().size()));
                    //Log.d("COUNT", "Count Data : " + covidViewModel.countData());
               // }
            //});
        }
    };

    private Thread deleteAll = new Thread(){
        @Override
        public void run(){
            super.run();
            //CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
            //CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                //@Override
                //public void run() {
                covidViewModel.delete();
                LiveData<List<Data>> datas = covidViewModel.getAllDatas();
                    //DataDao dataDao = database.dataDao();
                    //dataDao.deleteAll();
                    //List<Data> datas = dataDao.getAll();

                    Log.d("DataDeletedALL", "DataDeleted, Size : " +datas.getValue().size());
                //}
            //});
        }
    };

}