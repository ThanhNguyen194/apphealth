package com.thesis.apphealth.ui.askdoctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thesis.apphealth.R;

public class askdoctor extends Fragment {

    private AskdoctorViewModel mViewModel;
    Button sentSymptom,showresult;

    public static askdoctor newInstance() {
        return new askdoctor();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.askdoctor_fragment, container, false);
        sentSymptom=root.findViewById(R.id.choosesendsymptom);
        showresult=root.findViewById(R.id.chooseresult);
        sentSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller= Navigation.findNavController(view);
                controller.navigate(R.id.action_askdoctor_to_sendsymptom);
            }
        });
        showresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller= Navigation.findNavController(view);
                controller.navigate(R.id.action_askdoctor_to_listresult);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AskdoctorViewModel.class);
        // TODO: Use the ViewModel
    }

}
