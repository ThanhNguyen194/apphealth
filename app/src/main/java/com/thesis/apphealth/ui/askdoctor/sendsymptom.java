package com.thesis.apphealth.ui.askdoctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.apphealth.R;
import com.thesis.apphealth.data.medicalRecord;

import java.util.Random;

public class sendsymptom extends Fragment {

    private SendsymptomViewModel mViewModel;
    EditText date,time,symptominput,drugs,medhistory;
    DatabaseReference reference;
    FirebaseDatabase database;
    Button sendbtn;

    public static sendsymptom newInstance() {
        return new sendsymptom();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.sendsymptom_fragment, container, false);
        date=root.findViewById(R.id.inputdate);
        time=root.findViewById(R.id.inputime);
        symptominput=root.findViewById(R.id.symptom_inputtext);
        drugs=root.findViewById(R.id.inputdrug);
        medhistory=root.findViewById(R.id.medicalhistory_input_text);
        sendbtn=root.findViewById(R.id.sendsymptom_button);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("medicalRecord");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String medicalRecordID = null;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    int mednewID = new Random().nextInt(1000);
                    medicalRecordID = String.valueOf(mednewID);
                    String medID=snapshot.getKey();
                    if(medicalRecordID.equals(medID)){
                        mednewID=new Random().nextInt(1000)+1000;
                        medicalRecordID = String.valueOf(mednewID);
                    }
                }
                final String finalMedicalRecordID = medicalRecordID;
                sendbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String Date=date.getText().toString();
                                String Time=time.getText().toString();
                                String SymptomInput=symptominput.getText().toString();
                                String Drugs=drugs.getText().toString();
                                String MedHistory=medhistory.getText().toString();
                                Bundle bundle = getArguments();
                                String UID = "AQxsqDDfPIdFL2XulVzn79AVOKD3";
                                if (bundle != null) {
                                    UID=bundle.getString("userName");
                                    System.out.println("TEST"+bundle.getString("userName"));
                                }
                                medicalRecord medical=new medicalRecord(UID,Date,Time,SymptomInput,MedHistory,Drugs);
                                reference.child(finalMedicalRecordID).setValue(medical);
                                Toast.makeText(getActivity(),"Gửi thành công",Toast.LENGTH_SHORT).show();
                                NavController navController= Navigation.findNavController(view);
                                navController.navigate(R.id.action_sendsymptom_to_nav_home);
                            }
                        });
                    }
                });

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
        mViewModel = ViewModelProviders.of(this).get(SendsymptomViewModel.class);
        // TODO: Use the ViewModel
    }

}
