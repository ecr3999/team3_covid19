package com.example.team3_covid19.CovidMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.team3_covid19.R;
import com.example.team3_covid19.CovidMenu.room.Data;

public class CovidViewHolder extends RecyclerView.ViewHolder{
    private final ImageView ivAvatar;
    private final TextView tvCountryName;
    private final ImageButton btnFav ;
    private static Context context = null;
    private CovidViewHolder(View itemView) {
        super(itemView);
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        tvCountryName = itemView.findViewById(R.id.tvCountryName);
        btnFav = itemView.findViewById(R.id.btnFav);
    }

    public void bind(Data data) {
        Glide.with(context)
                .load(data.countryInfoFlag)
                .apply(new RequestOptions().override(200,100))
                .into(ivAvatar);
        tvCountryName.setText(data.country);
    }

    static CovidViewHolder create(ViewGroup parent) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_item, parent, false);

        return new CovidViewHolder(view);
    }
}
