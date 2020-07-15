package com.thesis.apphealth.ui.medicalInformation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.apphealth.R;

public class medicalInformation extends Fragment {
    DatabaseReference databaseReference;
    TextInputLayout Height,Weight,healthInsuranceID,medicalHistory,Bloodtype;
    private MedicalInformationViewModel mViewModel;

    public static medicalInformation newInstance() {
        return new medicalInformation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.medical_information_fragment, container, false);
        Height=root.findViewById(R.id.height);
        Weight=root.findViewById(R.id.weight);
        healthInsuranceID=root.findViewById(R.id.healthInsuranceID);
        medicalHistory=root.findViewById(R.id.medicalHistory);
        Bloodtype=root.findViewById(R.id.bloodtype);
        Height.getEditText().setText("1.80m");
        Weight.getEditText().setText("70kg");
        healthInsuranceID.getEditText().setText("HI12454");
        medicalHistory.getEditText().setText("Không có");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("testuser");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String bloodtype = dataSnapshot.child("bloodtype").getValue(String.class);
                Bloodtype.getEditText().setText(bloodtype);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MedicalInformationViewModel.class);
        // TODO: Use the ViewModel
    }

}
