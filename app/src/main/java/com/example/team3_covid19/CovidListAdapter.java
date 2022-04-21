package com.example.team3_covid19;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.team3_covid19.room.Data;

public class CovidListAdapter extends ListAdapter<Data, CovidViewHolder> {
    private OnItemClick click;

    public interface OnItemClick {
        void onItemClick(int position, Data data);
        //void onItemClick(int position, CovidData data);
    }

    public void setOnClickListener(OnItemClick onClick) {
        click = onClick;
    }

    public CovidListAdapter(@NonNull DiffUtil.ItemCallback<Data> diffCallback) {
        super(diffCallback);
    }

    @Override
    public CovidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CovidViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(CovidViewHolder holder, int position) {
        Data current = getItem(position);
        holder.bind(current.country);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onItemClick(position, current);
            }
        });
        /*TextView tvCountryName;
        tvCountryName = holder.findViewById(R.id.countryName);
        holder.itemView..setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onItemClick(position, data[position]);
            }
        });*/
        //Toast.makeText(CovidListAdapter.this, "COuntry: "+current.country, Toast.LENGTH_SHORT).show();
    }

    static class DataDiff extends DiffUtil.ItemCallback<Data> {

        @Override
        public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.country.equals(newItem.country);
        }
    }
}
