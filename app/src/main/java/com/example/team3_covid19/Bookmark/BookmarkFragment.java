package com.example.team3_covid19.Bookmark;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.team3_covid19.Profile.ProfileDataFragment;
import com.example.team3_covid19.Profile.SessionManagement;
import com.example.team3_covid19.R;
import com.example.team3_covid19.CovidMenu.room.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookmarkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookmarkFragment extends Fragment implements BookmarkListAdapter.OnItemClick{
    List<Data> data;
    List<Data> dataTemp;
    private BookmarkListAdapter adapter;
    private FavViewModel mFavViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    public BookmarkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment BookmarkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookmarkFragment newInstance() {
        BookmarkFragment fragment = new BookmarkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(int position, Data data) {
        Log.e("ClickedItem", data.country + data.countryInfoId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
                Log.e("DATATEMP",dataTemp.size()+"");
            }
        });
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
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}