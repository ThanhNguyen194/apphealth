package com.thesis.apphealth.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.apphealth.R;

import java.util.ArrayList;


public class MylistdiseseRecyclerViewAdapter extends RecyclerView.Adapter<MylistdiseseRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> mValues;
    private final ArrayList<String> symptoms;

    public MylistdiseseRecyclerViewAdapter(ArrayList<String> items,ArrayList<String>symptoms) {
        mValues = items;
        this.symptoms=symptoms;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_listdisese, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem.setText(mValues.get(position));
        holder.mIdView.setText(symptoms.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(v);
                Bundle bundle=new Bundle();
                bundle.putString("diseaseresult",mValues.get(position));
                navController.navigate(R.id.action_nav_listdisease_to_diseaseInformation,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public TextView mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItem =(TextView)view.findViewById(R.id.Disease_name);
            mIdView = (TextView) view.findViewById(R.id.Symptom_name);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
