package com.example.team3_covid19;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.team3_covid19.CovidMenu.CovidListAdapter;
import com.example.team3_covid19.CovidMenu.CovidListFragment;
import com.example.team3_covid19.Profile.LoginActivity;
import com.example.team3_covid19.Profile.SessionManagement;
import com.example.team3_covid19.CovidMenu.room.Data;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CovidListAdapter.OnItemClick{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CovidListFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

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

    @Override
    public void onItemClick(int position, Data data) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}