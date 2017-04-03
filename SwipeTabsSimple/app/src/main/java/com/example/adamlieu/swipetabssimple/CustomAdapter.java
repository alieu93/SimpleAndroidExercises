package com.example.adamlieu.swipetabssimple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Adam Lieu on 3/14/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<UpcomingReleases> dataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDate;

        public MyViewHolder(View itemView){
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textName);
            this.textViewDate = (TextView) itemView.findViewById(R.id.textDate);
        }
    }
    public CustomAdapter(ArrayList<UpcomingReleases> data){
        this.dataset = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        view.setOnClickListener(FragmentOne.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition){
        TextView textViewName = holder.textViewName;
        TextView textViewDate = holder.textViewDate;
        textViewName.setText(dataset.get(listPosition).getTitleName());
        textViewDate.setText(dataset.get(listPosition).getReleaseDate());
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }
}
