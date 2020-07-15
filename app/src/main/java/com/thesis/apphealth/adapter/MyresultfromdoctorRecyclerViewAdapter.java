package com.thesis.apphealth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thesis.apphealth.R;

import java.util.ArrayList;


public class MyresultfromdoctorRecyclerViewAdapter extends RecyclerView.Adapter<MyresultfromdoctorRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> date;
    private final ArrayList<String> time;
    private final ArrayList<String> symptom;

    public MyresultfromdoctorRecyclerViewAdapter(ArrayList<String> date, ArrayList<String> time,ArrayList<String> symptom) {
        this.date = date;
        this.time = time;
        this.symptom=symptom;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resultfromdoctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.sentdate.setText(date.get(position));
        holder.senttime.setText(time.get(position));
        holder.sentsymtom.setText(symptom.get(position));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView; 
        public final TextView sentdate;
        public final TextView senttime;
        public final TextView mContentView;
        public final TextView sentsymtom;
        public TextView mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            sentdate = (TextView) view.findViewById(R.id.send_date);
            senttime = (TextView) view.findViewById(R.id.send_time);
            mContentView = (TextView) view.findViewById(R.id.content);
            sentsymtom = (TextView) view.findViewById(R.id.sent_symptom);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
