package com.thesis.apphealth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.thesis.apphealth.R;
import com.thesis.apphealth.data.UserInfomation;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder>{
    private List<? extends UserInfomation> userInfomations;
    void setUserInfomations(final List<?extends UserInfomation >fullname){
        userInfomations =fullname;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_information_fragment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(userInfomations.get(position));
    }

    @Override
    public int getItemCount() {
        return userInfomations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextInputEditText fullName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullname);
        }
    }
}
