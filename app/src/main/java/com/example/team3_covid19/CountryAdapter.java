package com.example.team3_covid19;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    public CountryAdapter(List<CovidData> covidData) {
        this.covidData = covidData;
    }
    private List<CovidData> covidData;
    private FragmentManager fm;
    private Context mContext;
    CovidDetailFragment covidDetailFragment;
    private FragmentActivity fragmentClass;
    private CovidViewModel covidViewModel;
    private OnItemClick click;

    public interface OnItemClick {
        void onItemClick(int position, CovidData data);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item,parent,false);
        return new ViewHolder(view);
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
                click.onItemClick(position, data);
                //Toast.makeText(v.getContext(), tvCountryName.getText(), Toast.LENGTH_SHORT).show();

                /*FragmentManager fragmentManager = fragmentClass.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment covidDetailFragment = new CovidDetailFragment();
                fragmentTransaction.replace(R.id.container_fragment_covid_detail, covidDetailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/


                /*List<Data> datas = covidViewModel.getAllDatas();
                Log.d("GetData", "Size : " +datas.size());
                Log.d("GetData", "Data, Detail : " +datas);*/



                //CovidDetailFragment covidDetailFragment = new CovidDetailFragment();
                //MainActivity activity = (MainActivity) v.getContext();
                //activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment_covid_detail, covidDetailFragment).addToBackStack(null).commit();
                //if (savedInstanceState == null) {
                /*fragmentClass.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, CovidDetailFragment.newInstance())
                            .commitNow();*/

                //View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_covid_detail,mContext,false);

                //}
                //CovidDetailFragment covidDetailFragment = new CovidDetailFragment();
               // covidDetailFragment.show(fm, "Sample Fragment");
                //Log.i(TAG, "click on save button");
                //View inflatedView = LayoutInflater.from(this.itemView.getContext()).inflate(R.layout.fragment_covid_detail, this.itemView.getContext(), false);

                //Fragment f = getSupportFragmentManager().findFragmentById(R.id.day3_container);
                /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
                    // Inflate the layout for this fragment
                    return inflater.inflate(R.layout.fragment_covid_list, container, false);
                }*/
                //Toast.makeText(v.getContext(), tvCountryName.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
