package com.thesis.apphealth.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.apphealth.R;

import java.util.ArrayList;

public class symptomSearchAdapter extends RecyclerView.Adapter<symptomSearchAdapter.SearchSymptomViewHolder> {
    ArrayList<String> symptomList;
    Context context;
    private chooseSymptomListener symptomListener;
    public symptomSearchAdapter(Context context,ArrayList<String> symptomList,chooseSymptomListener symptomListener){
        this.context=context;
        this.symptomList=symptomList;
        this.symptomListener=symptomListener;
    }
    class SearchSymptomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView symptomItem;
        chooseSymptomListener symptomListener;

        public SearchSymptomViewHolder(@NonNull View itemView) {
            super(itemView);
            symptomItem=(TextView) itemView.findViewById(R.id.symptom_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


        }



/*        @Override
        public void onClick(View view) {
            symptomListener.chooseSymptomClick(getAdapterPosition());
        }*/
    }

    @NonNull
    @Override
    public symptomSearchAdapter.SearchSymptomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.symptom_search_item,parent,false);
        SearchSymptomViewHolder viewHolder = new SearchSymptomViewHolder(view);
        return new symptomSearchAdapter.SearchSymptomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSymptomViewHolder holder, final int position) {
        holder.symptomItem.setText(symptomList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        if(symptomListener != null) {
                            int position = getClickedPosition(view);

                            if (position != RecyclerView.NO_POSITION) {
                                symptomListener.chooseSymptomClicked(position);
                            }
                        }
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return symptomList.size();
    }
    public interface chooseSymptomListener{
        void chooseSymptomClicked(int position);
    }
private int getClickedPosition(View clickedView)
{
    RecyclerView recyclerView = (RecyclerView) clickedView.getParent();
    SearchSymptomViewHolder currentViewHolder = (SearchSymptomViewHolder) recyclerView.getChildViewHolder(clickedView);
    return currentViewHolder.getAdapterPosition();
}
}
