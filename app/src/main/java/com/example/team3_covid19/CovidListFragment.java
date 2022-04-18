package com.example.team3_covid19;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CovidListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CovidListFragment extends Fragment {
    private RecyclerView recyclerView;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.getAPI().getCovidData().enqueue(new Callback<List<CovidData>>()
        {
            @Override
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                View view = inflater.inflate(R.layout.fragment_covid_list, container, false);

                List<CovidData> covidData = response.body();
                CountryAdapter countryAdapter = new CountryAdapter(covidData);
                recyclerView = view.findViewById(R.id.recyclerview);
                recyclerView.setAdapter(countryAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onFailure(Call<List<CovidData>> call, Throwable t) {
                Toast.makeText(getActivity(), "An error has occured", Toast.LENGTH_LONG).show();
                Log.e("Failure:", t.getMessage());
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid_list, container, false);
    }
}