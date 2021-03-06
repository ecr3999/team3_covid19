package com.example.team3_covid19.CovidMenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.team3_covid19.Bookmark.BookmarkFragment;
import com.example.team3_covid19.Bookmark.room.FavViewModel;
import com.example.team3_covid19.CovidMenu.retrofit.CovidData;
import com.example.team3_covid19.Profile.LoginActivity;
import com.example.team3_covid19.Profile.ProfileDataFragment;
import com.example.team3_covid19.R;
import com.example.team3_covid19.Profile.SessionManagement;
import com.example.team3_covid19.UserClickableCallback;
import com.example.team3_covid19.CovidMenu.room.CovidViewModel;
import com.example.team3_covid19.CovidMenu.room.Data;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class CovidDetailFragment extends Fragment {
    private static Data data;
    private CovidViewModel mCovidViewModel;
    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;
    private FavViewModel mFavViewModel;
    private ImageButton btnFav;

    private final UserClickableCallback userClickableCallback = new UserClickableCallback() {
        @Override
        public void onClick(View view, CovidData covidData) {
            Toast.makeText(view.getContext(), "Hello" + covidData.getCountry(), Toast.LENGTH_SHORT).show();
        }
    };

    public CovidDetailFragment() {
        // Required empty public constructor
    }

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
        ft.replace(R.id.simpleFrameLayout, StatFragment.newInstance(data));
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = StatFragment.newInstance(data);
                        break;
                    case 1:
                        fragment = InfoFragment.newInstance(data);
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
        tvTitleCountry.setText(data.country);
        tvContinent.setText(data.continent);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnFav = view.findViewById(R.id.btnFav);
        mFavViewModel = new ViewModelProvider(this).get(FavViewModel.class);
        mFavViewModel.getAllDatas();
        boolean isExist = mFavViewModel.isCountryExist(data.getCountry());
        if(isExist) {
            btnFav.setImageResource(R.drawable.ic_fav);
        }
        else
            btnFav.setImageResource(R.drawable.ic_not_fav);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExist) //remove if in the list
                {
                    btnFav.setImageResource(R.drawable.ic_not_fav);
                    mFavViewModel.delete(data.country);
                }
                else{ //add if not in the list
                    btnFav.setImageResource(R.drawable.ic_fav);
                    List<Data> dataList = new ArrayList<>();
                    dataList.add(data);
                    mFavViewModel.insert(dataList);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backHome:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, CovidListFragment.newInstance())
                        .addToBackStack("home")
                        .commit();
                return true;
            case R.id.favorites:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, BookmarkFragment.newInstance())
                        .addToBackStack("favorites")
                        .commit();
                return true;
            case R.id.profile:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, ProfileDataFragment.newInstance())
                        .addToBackStack("profile")
                        .commit();
                return true;
            case R.id.logout:
                SessionManagement.getInstance().endUserSession(getActivity());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                Log.e("LOGOUT", item.getItemId()+"");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().invalidateOptionsMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_app_without_search, menu);
    }

}