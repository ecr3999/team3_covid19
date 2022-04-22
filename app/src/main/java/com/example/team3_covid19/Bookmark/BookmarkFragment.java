package com.example.team3_covid19.Bookmark;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.team3_covid19.Bookmark.room.FavViewModel;
import com.example.team3_covid19.CovidMenu.CovidDetailFragment;
import com.example.team3_covid19.CovidMenu.CovidListFragment;
import com.example.team3_covid19.Profile.LoginActivity;
import com.example.team3_covid19.Profile.ProfileDataFragment;
import com.example.team3_covid19.Profile.SessionManagement;
import com.example.team3_covid19.R;
import com.example.team3_covid19.CovidMenu.room.Data;

import java.util.ArrayList;
import java.util.List;


public class BookmarkFragment extends Fragment implements BookmarkListAdapter.OnItemClick{
    List<Data> data;
    List<Data> dataTemp;
    private BookmarkListAdapter adapter;
    private FavViewModel mFavViewModel;

    public BookmarkFragment() {

    }

    public static BookmarkFragment newInstance() {
        BookmarkFragment fragment = new BookmarkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(int position, Data data, String action) {
        if (action.equals("detail"))
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container, CovidDetailFragment.newInstance(data));
            ft.addToBackStack("Back");
            ft.commit();
        }
        else if(action.equals("unfav")){
            mFavViewModel.delete(data.getCountry());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
        dataTemp = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.rvBookmark);
        mFavViewModel = new ViewModelProvider(this).get(FavViewModel.class);
        adapter = new BookmarkListAdapter(new BookmarkListAdapter.DataDiff());
        mFavViewModel.getAllDatas().observe(getActivity(), datas -> {
            // Update the cached copy of the words in the adapter.
            if(datas.size()>0){
                adapter.submitList(datas);
                data = datas;
                dataTemp.clear();
                dataTemp.addAll(datas);
            }
        });
        adapter.setOnClickListener(BookmarkFragment.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_app_without_search, menu);
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
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().invalidateOptionsMenu();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}