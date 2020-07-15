package com.thesis.apphealth.ui.personalInformation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.apphealth.R;
import com.thesis.apphealth.adapter.userAdapter;

public class personInformation extends Fragment {
    TextInputEditText fullName,email,phoneNo,birtday;
    TextInputLayout fullNamelabel,addressLabel,birtdayLabel,bloodtypeLabel,emailLabel,nationalIDLabel,phoneNoLabel,sexLabel;
    private PersonInformationViewModel mViewModel;
    private RecyclerView recyclerView;
    private userAdapter userAdapter;
    private String userID;
    FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener authStateListener;
    DatabaseReference databaseReference;

    public static personInformation newInstance() {
        return new personInformation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.person_information_fragment, container, false);
        fullName = root.findViewById(R.id.fullname);
        fullNamelabel = root.findViewById(R.id.fullnamelabel);
        addressLabel=root.findViewById(R.id.addressLabel);
        birtdayLabel=root.findViewById(R.id.birthdayLabel);
        bloodtypeLabel=root.findViewById(R.id.bloodtypeLabel);
        nationalIDLabel=root.findViewById(R.id.nationalIDLabel);
        phoneNoLabel=root.findViewById(R.id.phoneNoLabel);
        sexLabel=root.findViewById(R.id.sexLabel);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child("testuser");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fullName = dataSnapshot.child("fullName").getValue(String.class);
                String address = dataSnapshot.child("address").getValue(String.class);
                String birthday = dataSnapshot.child("birthday").getValue(String.class);
                String bloodtype = dataSnapshot.child("bloodtype").getValue(String.class);
                String nationalID = dataSnapshot.child("nationalID").getValue(String.class);
                String phoneNo = dataSnapshot.child("phoneNo").getValue(String.class);
                String sex = dataSnapshot.child("sex").getValue(String.class);
                fullNamelabel.getEditText().setText(fullName);
                addressLabel.getEditText().setText(address);
                birtdayLabel.getEditText().setText(birthday);
                bloodtypeLabel.getEditText().setText(bloodtype);
                nationalIDLabel.getEditText().setText(nationalID);
                phoneNoLabel.getEditText().setText(phoneNo);
                sexLabel.getEditText().setText(sex);
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
        mViewModel = ViewModelProviders.of(this).get(PersonInformationViewModel.class);
    }
}
