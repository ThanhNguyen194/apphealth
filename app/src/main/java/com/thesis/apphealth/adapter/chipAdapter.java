package com.thesis.apphealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.thesis.apphealth.R;

import java.util.ArrayList;

public class chipAdapter extends RecyclerView.Adapter<chipAdapter.ChipViewHolder> {
    ArrayList<String> chipList;
    Context context;
    private ChipListener chipListener;
    public chipAdapter(ArrayList<String>chipList){
        this.chipList=chipList;
    }

    @NonNull
    @Override
    public chipAdapter.ChipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);
        return new chipAdapter.ChipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChipViewHolder holder, int position) {
        holder.chipItem.setText(chipList.get(position));


    }

    @Override
    public int getItemCount() {
        return chipList.size();
    }

    public class ChipViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Chip chipItem;
        public ChipViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface ChipListener{
        void chooseChipClick(String chip,int position);
    }
}
