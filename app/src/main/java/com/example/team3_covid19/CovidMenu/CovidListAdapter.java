package com.example.team3_covid19.CovidMenu;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.team3_covid19.CovidMenu.room.Data;

public class CovidListAdapter extends ListAdapter<Data, CovidViewHolder> {
    private OnItemClick itemClick;

    public interface OnItemClick {
        void onItemClick(int position, Data data);
    }

    public void setOnClickListener(OnItemClick onClick) {
                itemClick = onClick;
    }

    public CovidListAdapter(@NonNull DiffUtil.ItemCallback<Data> diffCallback) {
        super(diffCallback);
    }

    @Override
    public CovidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CovidViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(CovidViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Data current = getItem(position);
        System.out.println(current.countryInfoFlag);
        holder.bind(current);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onItemClick(position, current);
            }
        });
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
