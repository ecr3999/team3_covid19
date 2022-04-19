package com.example.team3_covid19;

import static android.content.ContentValues.TAG;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestDbActivity extends AppCompatActivity {
   //EditText txtCountry,txtCase;
    private TextInputLayout txtCountry,txtCase;
    private static final int NUMBER_OF_THREADS = 1;
    //private LiveData<List<Data>> allData;
    private List<Data> allData;
    private Executor poolWorker = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        txtCountry = findViewById(R.id.txtInputCountry);
        txtCase = findViewById(R.id.txtInputCase);
        String country = txtCountry.getEditText().toString();
        String cases = txtCase.getEditText().toString();

        Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
                CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        startThread();
                    }
                });
                //covidRepository.insert(data);
                //covidRepository.insert(data);
            }
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
                CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        DataDao dataDao = database.dataDao();
                        dataDao.deleteAll();
                        Log.d("DELETE","Data Deleted");
                    }
                });
            }
        });

        Button btnGetData = findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
                CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        DataDao dataDao = database.dataDao();
                        allData = dataDao.getAll();
                        Log.d("JmlDATA", "Size Data:" + allData.size());
                        Log.d("JmlDATA", "Data:" + allData.toString());
                    }
                });
                //List<Data> users = dataDao.getAll();
               // datas = covidRepository.getAllDatas();
                //allData =
                //Toast.makeText(TestDbActivity.this,"Data : " + datas,Toast.LENGTH_SHORT).show();
                //Log.d("DATA :", "Size :" + datas.size());
                //Log.d("DATA :", "Value :" + datas);
            }
        });

        //covidRepository.insert(data);
        /*Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String country = txtCountry.getEditText().toString();
                String cases = txtCase.getEditText().toString();

                Data data = new Data();
                data.country = "tes";
                data.cases = "12";
                //covidRepository.insert(data);
                covidRepository.insert(data);
            }
        });*/
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
                    Log.d("Masuk", "Masuk ke thread");
                    //dataDao.deleteAll();
                    for(int i=0;i<10;i++){
                        Data data = new Data();
                        data.country = "Country"+i;
                        data.cases = "Cases"+i;
                        dataDao.insert(data);
                        Log.d("DataInserted", data.country);
                    }
                }
            });
        }
    };

    private Thread deleteData = new Thread(){
        @Override
        public void run(){
            super.run();
            CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
            CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    DataDao dataDao = database.dataDao();
                    dataDao.deleteAll();
                }
            });
        }
    };
}
