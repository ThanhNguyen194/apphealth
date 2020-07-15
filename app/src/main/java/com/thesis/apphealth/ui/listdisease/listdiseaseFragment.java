package com.thesis.apphealth.ui.listdisease;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
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
import com.thesis.apphealth.adapter.MylistdiseseRecyclerViewAdapter;

import java.util.ArrayList;
public class listdiseaseFragment extends Fragment {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    ArrayList<String> listsymptom=new ArrayList<>();
    ArrayList<String> diseaseList=new ArrayList<String>();
    ArrayList<String> getListsymptom=new ArrayList<String>();
    MylistdiseseRecyclerViewAdapter adapter;

    public listdiseaseFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static listdiseaseFragment newInstance(int columnCount) {
        listdiseaseFragment fragment = new listdiseaseFragment();
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
            Bundle bundle;
            bundle = getArguments();
            assert bundle != null;
            listsymptom = bundle.getStringArrayList("choosedList");
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                listsymptom.clear();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listdisese_list, container, false);
        recyclerView=view.findViewById(R.id.listdisease);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        setAdapter();
        // Set the adapter
        return view;
    }
    private void setAdapter(){

            databaseReference.child("1zprxaVyJAD0wLz8BT-rXHotENYmHrlBB_x0XrMGQ5O0").child("disease").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int count = 0;
                    String symptomName;
                    String diseaseName;
                    recyclerView.removeAllViews();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        symptomName = snapshot.child("diseasesymptom").getValue(String.class);
                        diseaseName = snapshot.child("diseaseName").getValue(String.class);
                        for (int i = 0; i < listsymptom.size(); i++) {
                            final String choosedString = listsymptom.get(i);
                            if (symptomName.contains(choosedString)) {
                                count++;
                            }
                        }
                        if (count>1) {
                            getListsymptom.add(symptomName);
                            diseaseList.add(diseaseName);
                            adapter = new MylistdiseseRecyclerViewAdapter(diseaseList, getListsymptom);
                            recyclerView.setAdapter(adapter);
                            count=0;
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
    public boolean onBackPressed() {

        return true;
    }

}
