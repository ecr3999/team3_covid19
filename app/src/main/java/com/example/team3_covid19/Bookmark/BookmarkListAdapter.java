package com.example.team3_covid19.Bookmark;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.team3_covid19.CovidMenu.room.Data;

public class BookmarkListAdapter extends ListAdapter<Data, BookmarkViewHolder> {
    private OnItemClick itemClick;

    public interface OnItemClick {
        void onItemClick(int position, Data data);
    }

    public void setOnClickListener(OnItemClick onClick) {
        itemClick = onClick;
    }

    public BookmarkListAdapter(@NonNull DiffUtil.ItemCallback<Data> diffCallback) {
        super(diffCallback);
    }

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BookmarkViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(BookmarkViewHolder holder, int position) {
        Data current = getItem(position);
        holder.bind(current);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onItemClick(position, current);
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
