package com.example.team3_covid19;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_covid19.TabFragment.InfoFragment;
import com.example.team3_covid19.TabFragment.StatFragment;
import com.example.team3_covid19.room.CovidViewModel;
import com.example.team3_covid19.room.Data;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CovidDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CovidDetailFragment extends Fragment {
    private static int idCountry;
    private static Data data;
    private RecyclerView recyclerView;
    private CovidViewModel mCovidViewModel;
    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;

    private final UserClickableCallback userClickableCallback = new UserClickableCallback() {
        @Override
        public void onClick(View view, CovidData covidData) {
            Toast.makeText(view.getContext(),"Hello" + covidData.getCountry(), Toast.LENGTH_SHORT).show();
        }
    };

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    public CovidDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CovidListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CovidDetailFragment newInstance(Data dataCovid) {
            CovidDetailFragment fragment = new CovidDetailFragment();
            data = dataCovid;
            return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); //Need to be included if created in fragment
        mCovidViewModel = new ViewModelProvider(this).get(CovidViewModel.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid_detail, container, false);

        simpleFrameLayout = (FrameLayout) view.findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        //set default fragment on load
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.simpleFrameLayout, new InfoFragment());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               Fragment fragment = null;
               switch (tab.getPosition()) {
                   case 0:
                       fragment = InfoFragment.newInstance(data);
                       break;
                   case 1:
                       fragment = StatFragment.newInstance(data);
                       break;
               }
               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.replace(R.id.simpleFrameLayout, fragment);
               ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
               ft.commit();
           }
           @Override
           public void onTabUnselected(TabLayout.Tab tab) {
           }
           @Override
           public void onTabReselected(TabLayout.Tab tab) {
           }
       });

        TextView tvTitleCountry = view.findViewById(R.id.tvTitleCountry);
        TextView tvContinent = view.findViewById(R.id.tvContinent);
        //ImageView imgFlag = view.findViewById(R.id.imgFlag);

        //Picasso.get().load(data.countryInfoFlag).into(imgFlag);
        tvTitleCountry.setText(data.country);
        tvContinent.setText(data.continent);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

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