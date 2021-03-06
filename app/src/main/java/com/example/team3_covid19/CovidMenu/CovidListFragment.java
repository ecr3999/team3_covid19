package com.example.team3_covid19.CovidMenu;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.team3_covid19.Bookmark.BookmarkFragment;
import com.example.team3_covid19.CovidMenu.retrofit.CovidData;
import com.example.team3_covid19.Profile.LoginActivity;
import com.example.team3_covid19.Profile.ProfileDataFragment;
import com.example.team3_covid19.R;
import com.example.team3_covid19.CovidMenu.retrofit.RetrofitCovidData;
import com.example.team3_covid19.Profile.SessionManagement;
import com.example.team3_covid19.CovidMenu.room.CovidViewModel;
import com.example.team3_covid19.CovidMenu.room.Data;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidListFragment extends Fragment implements CovidListAdapter.OnItemClick {
    private RecyclerView recyclerView;
    List<Data> data;
    List<Data> dataTemp;
    private CovidListAdapter adapter;
    private CovidViewModel mCovidViewModel;
    boolean flagService;

    private static final int NUMBER_OF_THREADS = 1;
    private Executor poolWorker = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private Executor mainThread = new Executor() {
        private Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };

    public CovidListFragment() {
        // Required empty public constructor
    }

    public static CovidListFragment newInstance() {
        CovidListFragment fragment = new CovidListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //Need to be included if created in fragment
        mCovidViewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        getDataFromServer();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid_list, container, false);
        return view;
   }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
        dataTemp = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        mCovidViewModel = new ViewModelProvider(this).get(CovidViewModel.class);

        adapter = new CovidListAdapter(new CovidListAdapter.DataDiff());
        mCovidViewModel.getAllDatas().observe(getActivity(), datas -> {
            // Update the cached copy of the words in the adapter.
            if (datas.size() < 1 && flagService==false) {
                Toast.makeText(getActivity(), "Server bermasalah, coba beberapa saat lagi..", Toast.LENGTH_SHORT).show();
            }else{
                adapter.submitList(datas);
                data = datas;
                dataTemp.clear();
                dataTemp.addAll(datas);
                List<Data> data;
            }
        });
        adapter.setOnClickListener(CovidListFragment.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onItemClick(int position, Data data) {

        if(this.data.size() !=  dataTemp.size()){
            this.data.clear();
            this.data.addAll(dataTemp);
        };
        Log.e("TAG "+ this.data.size(), dataTemp.size()+"");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, CovidDetailFragment.newInstance(data));
        ft.addToBackStack("Back");
        ft.commit();
    }

    public void getDataFromServer() {
        flagService = true;
        RetrofitCovidData retrofitCovidData = new RetrofitCovidData();
        retrofitCovidData.getAPI().getCovidData().enqueue(new Callback<List<CovidData>>() {
            @Override
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {
                //hapus dulu data di db
                mCovidViewModel.deleteAll();
                List<Data> listData = new ArrayList<>();
                for (int i = 0; i < response.body().size(); i++) {
                    Data data = new Data();
                    data.updated = String.valueOf(response.body().get(i).getUpdated());
                    //get country info
                    data.countryInfoId = response.body().get(i).getCountryInfo().getId();
                    data.countryInfoIso2 = response.body().get(i).getCountryInfo().getIso2();
                    data.countryInfoIso3 = response.body().get(i).getCountryInfo().getIso3();
                    data.countryInfoLat = response.body().get(i).getCountryInfo().getLat();
                    data.countryInfoLong = response.body().get(i).getCountryInfo().getJsonMemberLong();
                    data.countryInfoFlag = response.body().get(i).getCountryInfo().getFlag();

                    data.country = response.body().get(i).getCountry();
                    data.continent = response.body().get(i).getContinent();
                    data.cases = response.body().get(i).getCases();
                    data.todayCases = response.body().get(i).getTodayCases();
                    data.deaths = response.body().get(i).getDeaths();
                    data.todayDeaths = response.body().get(i).getTodayDeaths();
                    data.recovered = response.body().get(i).getRecovered();
                    data.todayRecovered = response.body().get(i).getTodayRecovered();
                    data.population = response.body().get(i).getPopulation();
                    listData.add(data);
                }
                mCovidViewModel.insert(listData);
            }

            @Override
            public void onFailure(Call<List<CovidData>> call, Throwable t) {
               Log.e("Failure:", t.getMessage());
                flagService = false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorites:
                Toast.makeText(getActivity(), "Favorites", Toast.LENGTH_SHORT);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, BookmarkFragment.newInstance())
                        .addToBackStack("favorites")
                        .commit();
                getActivity().invalidateOptionsMenu();
                return true;
            case R.id.profile:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, ProfileDataFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.logout:
                SessionManagement.getInstance().endUserSession(getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_app, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (quesrySearch(s))
                    return true;
                else {
                    String message = "Data tidak ditemukan";
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.rootLayout), message, Snackbar.LENGTH_LONG);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                    return false;
                }
            }

            private boolean quesrySearch(String query) {
                ArrayList<Data> alFoundData = null;
                alFoundData = searchUser(query);
                if (alFoundData.size() != 0) {
                    data.clear();
                    data.addAll(alFoundData);
                    adapter.notifyDataSetChanged();
                    return true;
                } else
                    return false;
            }

            private ArrayList<Data> searchUser(String query) {
                ArrayList<Data> alFoundData = new ArrayList<Data>();
                for (Data i : data) {
                    if (i.country != null) {
                        if (i.country.toLowerCase().contains(query.toLowerCase())) {
                            alFoundData.add(i);
                        }
                    }
                }
                return alFoundData;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (TextUtils.isEmpty(s)) {
                    //reset data
                    data.clear();
                    data.addAll(dataTemp);
                    adapter.submitList(data);
                    adapter.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
    }
}