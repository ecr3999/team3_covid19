package com.example.team3_covid19.TabFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.team3_covid19.CovidDetailFragment;
import com.example.team3_covid19.R;
import com.example.team3_covid19.room.Data;

public class InfoFragment extends Fragment {
    private static Data data;
    public InfoFragment() {

    }

    public static InfoFragment newInstance(Data dataCovid) {
        InfoFragment fragment = new InfoFragment();
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
        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}
