package com.example.team3_covid19;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_covid19.room.CovidViewModel;
import com.example.team3_covid19.room.Data;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private List<Data> covidData;

    public CountryAdapter(List<Data> covidData) {

        covidData = new ArrayList<>();
        this.covidData = covidData;
    }

    private FragmentManager fm;
    private Context mContext;
    private FragmentActivity fragmentClass;
    private CovidViewModel covidViewModel;
    private OnItemClick click;

    public interface OnItemClick {
        void onItemClick(int position, Data data);
    }

    public void setOnClickListener(OnItemClick onClick) {
        click = onClick;
    }

    public CountryAdapter(Context context, FragmentManager fm) {
        this.fm = fm;
    }


    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Data data = covidData.get(position);
        holder.tvCountryName.setText(data.country);
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
                Data data = covidData.get(position);
                click.onItemClick(position, data);
            }
        }
    }
}
