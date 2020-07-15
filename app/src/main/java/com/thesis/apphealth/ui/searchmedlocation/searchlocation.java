package com.thesis.apphealth.ui.searchmedlocation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.textfield.TextInputEditText;
import com.thesis.apphealth.R;
import com.thesis.apphealth.ui.map.map;

public class searchlocation extends Fragment {

    private SearchlocationViewModel mViewModel;
    private TextInputEditText mSearchText;
    private GoogleMap mMap;


    public static searchlocation newInstance() {
        return new searchlocation();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.searchlocation_fragment, container, false);
        mSearchText=root.findViewById(R.id.search_medical_location);
        Fragment fragment = new map();
        FragmentManager fragmentManager= getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.commit();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SearchlocationViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
