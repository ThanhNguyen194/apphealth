package com.thesis.apphealth.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listDiseaseAdapter extends RecyclerView.Adapter<listDiseaseAdapter.ListDiseaseViewHolder> {
    ArrayList<String> diseaseList;
    Context context;
    public listDiseaseAdapter(Context context, ArrayList<String> diseaseList){
        this.context=context;
        this.diseaseList=diseaseList;
    }
    @NonNull
    @Override
    public ListDiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListDiseaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListDiseaseViewHolder extends RecyclerView.ViewHolder {
        public ListDiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
