package com.thesis.apphealth.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thesis.apphealth.R;

public class home extends Fragment {private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        return root;

    }


   /* private HomeViewModel mViewModel;

    public static home newInstance() {
        return new home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }*/

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        final NavController navigation = Navigation.findNavController(view);
        CardView btn_diagnosis,btn_drugreminder,btn_findmedlocation, btn_askdoctor;
        btn_diagnosis=view.findViewById(R.id.diagnosis);
        btn_drugreminder=view.findViewById(R.id.btn_drugreminder);
        btn_findmedlocation=view.findViewById(R.id.btn_find_medical_location);
        btn_askdoctor=view.findViewById(R.id.askdoctorcardview);
        btn_diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.navigate(R.id.action_nav_home_to_nav_diagnosis2);
            }
        });
        btn_drugreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.navigate(R.id.action_nav_home_to_nav_drugreminder);
            }
        });
        btn_findmedlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.navigate(R.id.action_nav_home_to_nav_searchlocation);
            }
        });
        btn_askdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigation.navigate(R.id.action_nav_home_to_askdoctor);
            }
        });


    }

}
