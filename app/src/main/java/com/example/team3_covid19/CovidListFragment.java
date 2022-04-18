package com.example.team3_covid19;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

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
    private final UserClickableCallback userClickableCallback = new UserClickableCallback() {
        @Override
        public void onClick(View view, CovidData covidData) {
            Toast.makeText(view.getContext(),"Hello" + covidData.getCountry(), Toast.LENGTH_SHORT).show();


//            DialogFragment newFragment = DeleteUserDialogFragment.newInstance(user);
//            newFragment.show(getChildFragmentManager(), "DeleteUserDialogFragment");
        }
    };

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
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        retrofitInstance.getAPI().getCovidData().enqueue(new Callback<List<CovidData>>()
        {
            @Override
            public void onResponse(Call<List<CovidData>> call, Response<List<CovidData>> response) {

                List<CovidData> covidData = response.body();
                CountryAdapter countryAdapter  = new CountryAdapter(covidData);

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
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
       /* SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (updateList(s))
                    return true;
                else {
                    String message = "Data tidak ditemukan";
                    Snackbar snackbar = Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG);
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

            private boolean updateList(String s) {
                    ArrayList<CovidData> alFoundPresidents = null;
                    alFoundPresidents = searchUser(query);

                    if (alFoundPresidents.size() != 0) {
                        alPresidents.clear();
                        alPresidents.addAll(alFoundPresidents);
                        itemAdapter.notifyDataSetChanged();
                        return true;
                    } else
                        return false;
                }

            @Override
            public boolean onQueryTextChange(String s) {

                if (TextUtils.isEmpty(s)) {
                    //reset data
                    resetData();
                    return true;
                }
                return false;
            }
        });*/

        super.onCreateOptionsMenu(menu, inflater);
        Log.e("Hello","test");
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case  R.id.favorites:
                Toast.makeText(getActivity(), "insert favorites here", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.search:
                Toast.makeText(getActivity(),"insert search here",Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

}