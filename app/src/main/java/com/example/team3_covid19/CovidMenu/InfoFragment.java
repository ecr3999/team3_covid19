package com.example.team3_covid19.CovidMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.team3_covid19.R;
import com.example.team3_covid19.CovidMenu.room.Data;

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
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView tvIso2 = view.findViewById(R.id.tvIso2);
        TextView tvIso3 = view.findViewById(R.id.tvIso3);
        TextView tvLat = view.findViewById(R.id.tvLat);
        TextView tvLong = view.findViewById(R.id.tvLong);
        TextView tvPopulation = view.findViewById(R.id.tvPopulation);
        ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
        tvIso2.setText(""+data.countryInfoIso2);
        tvIso3.setText(""+data.countryInfoIso3);
        tvLat.setText(""+data.countryInfoLat);
        tvLong.setText(""+data.countryInfoLong);
        tvPopulation.setText(""+data.population);
        Glide.with(view)
                .load(data.countryInfoFlag)
                .apply(new RequestOptions().override(400,160))
                .into(ivAvatar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
