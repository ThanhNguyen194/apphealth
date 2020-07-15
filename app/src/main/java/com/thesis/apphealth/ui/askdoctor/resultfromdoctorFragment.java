package com.thesis.apphealth.ui.askdoctor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.apphealth.R;
import com.thesis.apphealth.adapter.MyresultfromdoctorRecyclerViewAdapter;

import java.util.ArrayList;

public class resultfromdoctorFragment extends Fragment {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<String> listtime=new ArrayList<>();
    ArrayList<String> listdate=new ArrayList<String>();
    ArrayList<String> listsymptom=new ArrayList<String>();
    MyresultfromdoctorRecyclerViewAdapter adapter;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public resultfromdoctorFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static resultfromdoctorFragment newInstance(int columnCount) {
        resultfromdoctorFragment fragment = new resultfromdoctorFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultfromdoctor_list, container, false);
        recyclerView=view.findViewById(R.id.list);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        setAdapter();
        return view;
    }

    private void setAdapter() {
        databaseReference.child("medicalRecord").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String PatientID;
                String time;
                String date;
                String Symptom;
                String check;
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    PatientID = snapshot.child("patientID").getValue(String.class);
                    time = snapshot.child("time").getValue(String.class);
                    date = snapshot.child("date").getValue(String.class);
                    check = snapshot.child("check").getValue(String.class);
                    Symptom = snapshot.child("symptomListByPatient").getValue(String.class);
                    if (PatientID != null && PatientID.equals("AQxsqDDfPIdFL2XulVzn79AVOKD3")) {
                        if (check != null && check.equals("1")) {
                            listsymptom.add(Symptom);
                            listtime.add(time);
                            listdate.add(date);
                            adapter = new MyresultfromdoctorRecyclerViewAdapter(listdate, listtime, listsymptom);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
