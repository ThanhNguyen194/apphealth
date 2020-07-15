package com.thesis.apphealth.ui.diagnosisresult;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.apphealth.R;

public class diagnosis_result extends Fragment {
    DatabaseReference reference;
    TextView disease,diseaseInfo;
    String getdiseaseInfo;

    private DiagnosisResultViewModel mViewModel;

    public static diagnosis_result newInstance() {
        return new diagnosis_result();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.diagnosis_result_fragment, container, false);
        disease=root.findViewById(R.id.disease_show);
        diseaseInfo=root.findViewById(R.id.diseasedescrip_show);
        reference=FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getArguments();
        final String result=bundle.getString("diseaseresult");
        disease.setText(result);
        reference.child("1zprxaVyJAD0wLz8BT-rXHotENYmHrlBB_x0XrMGQ5O0").child("disease").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    String diseaseID=snapshot.getKey();
                    getdiseaseInfo = snapshot.child("diseaseInformation").getValue(String.class);
                    String diseaseName = snapshot.child("diseaseName").getValue(String.class);
                    if(result.equals(diseaseName)){
                        diseaseInfo.setText(getdiseaseInfo);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        if(getdiseaseInfo!=null) {
            diseaseInfo.setText(getdiseaseInfo);
        }else {
            String text="Chưa có thông tin về bệnh này";

        }
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DiagnosisResultViewModel.class);
        // TODO: Use the ViewModel
    }

}
