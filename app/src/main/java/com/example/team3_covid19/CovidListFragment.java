package com.example.team3_covid19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.team3_covid19.room.CovidDatabase;
import com.example.team3_covid19.room.CovidViewModel;
import com.example.team3_covid19.room.Data;
import com.example.team3_covid19.room.DataDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CovidListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CovidListFragment extends Fragment implements CountryAdapter.OnItemClick{
    private RecyclerView recyclerView;
    List<Data> covidData;
    private CovidViewModel covidViewModel;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    public CovidListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CovidListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CovidListFragment newInstance() {
        CovidListFragment fragment = new CovidListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //Need to be included if created in fragment

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        DataDao dataDao = CovidDatabase.getDatabase(getActivity()).dataDao();
        CovidViewModel covidViewModel;
        covidViewModel = new ViewModelProvider(getActivity()).get(CovidViewModel.class);
        covidData = covidViewModel.getAllDatas().getValue();
        CountryAdapter countryAdapter  = new CountryAdapter(covidData);
                countryAdapter.setOnClickListener(CovidListFragment.this);
                recyclerView = view.findViewById(R.id.recyclerview);
                recyclerView.setAdapter(countryAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //startThread();
    }

    @Override
    public void onItemClick(int position, Data data) {
        Log.e("TAG", data.country);
    }
}