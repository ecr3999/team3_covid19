package com.example.team3_covid19;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class TestDbActivity extends AppCompatActivity {
    EditText txtCountry,txtCase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        txtCountry =findViewById(R.id.txtCountryName);
        txtCase = findViewById(R.id.txtCase);
        Button btnInsert = findViewById(R.id.btnInsert);
        CovidDatabase database = Room.databaseBuilder(getApplicationContext(), CovidDatabase.class, "Covid19").build();
        DataDao dataDao = database.dataDao();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CovidDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String country = txtCountry.getText().toString();
                        String cases = txtCase.getText().toString();
                        dataDao.insertAll(new Data(country, cases));
                        Toast.makeText(TestDbActivity.this, "Data Saved!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button btnGetData = findViewById(R.id.btnGetData);
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Data> datas;
                datas = dataDao.getAll();
                Toast.makeText(TestDbActivity.this,"Data : " + datas,Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "run2 :" + users.size());
            }
        });
    }



}
