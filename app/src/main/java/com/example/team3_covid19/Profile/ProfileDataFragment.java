package com.example.team3_covid19.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.team3_covid19.Bookmark.BookmarkFragment;
import com.example.team3_covid19.CovidMenu.CovidListFragment;
import com.example.team3_covid19.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileDataFragment extends Fragment {
    public static final String LOGIN_DATA = "com.example.team3_covid19.Profile.LoginActivity.LOGIN_DATA";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO: Rename and change types of parameters
    public ProfileDataFragment() {
        // Required empty public constructor
    }

    public static ProfileDataFragment newInstance() {
        ProfileDataFragment fragment = new ProfileDataFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_data, container, false);
        SharedPreferences sharedPreferences;
        sharedPreferences = requireActivity().getSharedPreferences(LOGIN_DATA, Context.MODE_PRIVATE);
        String a = "";
        String username = sharedPreferences.getString("username", "");
        String fullname = sharedPreferences.getString("full_name", "");
        String email = sharedPreferences.getString("email", "");
        TextView tvUsername = view.findViewById(R.id.tvUsername);
        TextView tvFullname = view.findViewById(R.id.tvFullname);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        tvUsername.setText(username);
        tvFullname.setText(fullname);
        tvEmail.setText(email);
        return view;
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
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}