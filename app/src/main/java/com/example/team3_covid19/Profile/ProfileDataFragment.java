package com.example.team3_covid19.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
}