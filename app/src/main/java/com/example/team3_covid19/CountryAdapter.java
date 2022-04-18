package com.example.team3_covid19;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>  {
    public CountryAdapter(List<CovidData> covidData) {
        this.covidData = covidData;
    }

    private List<CovidData> covidData;
    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        CovidData data = covidData.get(position);
        holder.tvCountryName.setText(data.getCountry());
    }

    @Override
    public int getItemCount() {
        return covidData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCountryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = itemView.findViewById(R.id.countryName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAbsoluteAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                CovidData data = covidData.get(position);
                Toast.makeText(v.getContext(), tvCountryName.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
