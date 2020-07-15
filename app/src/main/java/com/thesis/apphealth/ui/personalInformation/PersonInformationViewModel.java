package com.thesis.apphealth.ui.personalInformation;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thesis.apphealth.data.FirebaseLiveData;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;

public class PersonInformationViewModel extends ViewModel {
    private List<Entity> mList = new ArrayList<>();
    private static final DatabaseReference dataRef =
            FirebaseDatabase.getInstance().getReference().child("fullName");
    @NonNull
    public LiveData<List<android.content.Entity>> getFullNameListLiveData(){
        FirebaseLiveData mLiveData = new FirebaseLiveData(dataRef);
        LiveData<List<Entity>> mFullNameLiveData = Transformations.map(mLiveData, new Deserializer());
        return getFullNameListLiveData();
    }
    private class Deserializer implements Function<DataSnapshot,List<Entity>>{
        @Override
        public List<Entity> apply(DataSnapshot dataSnapshot) {
            mList.clear();
            for(DataSnapshot snap : dataSnapshot.getChildren()){
                Entity fullname = snap.getValue(Entity.class);
                mList.add(fullname);
            }
            return mList;
        }
    }



}
