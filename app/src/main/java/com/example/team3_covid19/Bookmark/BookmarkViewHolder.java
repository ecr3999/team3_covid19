package com.example.team3_covid19.Bookmark;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.team3_covid19.Bookmark.room.FavViewModel;
import com.example.team3_covid19.R;
import com.example.team3_covid19.CovidMenu.room.Data;

public class BookmarkViewHolder extends RecyclerView.ViewHolder{
    private final ImageView ivAvatar;
    private final TextView tvCountryName;
    private final ImageButton btnDelete ;
    private static Context context = null;

    public ImageButton getBtnDelete() {
        return btnDelete;
    }

    private BookmarkViewHolder(View itemView) {
        super(itemView);
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        tvCountryName = itemView.findViewById(R.id.tvCountryName);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }

    public void bind(Data data) {
        Glide.with(context)
                .load(data.countryInfoFlag)
                .apply(new RequestOptions().override(200,100))
                .into(ivAvatar);
        tvCountryName.setText(data.country);
    }

    static BookmarkViewHolder create(ViewGroup parent) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bookmark_item, parent, false);

        return new BookmarkViewHolder(view);
    }
}
