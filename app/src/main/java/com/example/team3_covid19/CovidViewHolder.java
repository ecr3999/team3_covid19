package com.example.team3_covid19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CovidViewHolder extends RecyclerView.ViewHolder{
    private final TextView covidItemView;
    private CovidViewHolder(View itemView) {
        super(itemView);
        covidItemView = itemView.findViewById(R.id.textViewRc);
    }

    public void bind(String text) {
        covidItemView.setText(text);
    }

    static CovidViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new CovidViewHolder(view);
    }
}
