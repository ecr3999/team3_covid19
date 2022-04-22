package com.example.team3_covid19.CovidMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.team3_covid19.R;
import com.example.team3_covid19.CovidMenu.room.Data;

public class StatFragment extends Fragment {
    private static Data data;
    public StatFragment() {

    }

    public static StatFragment newInstance(Data dataCovid) {
        StatFragment fragment = new StatFragment();
        data = dataCovid;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stat, container, false);
        TextView tvCase = view.findViewById(R.id.tvCase);
        TextView tvDeath = view.findViewById(R.id.tvDeath);
        TextView tvRecovered = view.findViewById(R.id.tvRecovered);
        TextView tvTodayCase = view.findViewById(R.id.tvTodayCase);
        TextView tvTodayDeath = view.findViewById(R.id.tvTodayDeath);
        TextView tvTodayRecovered = view.findViewById(R.id.tvTodayRecovered);
        tvCase.setText(data.cases);
        tvDeath.setText(""+data.deaths);
        tvRecovered.setText(""+data.recovered);
        tvTodayCase.setText(""+data.todayCases);
        tvTodayDeath.setText(""+data.todayDeaths);
        tvTodayRecovered.setText(""+data.todayRecovered);
        return view;
    }
}
