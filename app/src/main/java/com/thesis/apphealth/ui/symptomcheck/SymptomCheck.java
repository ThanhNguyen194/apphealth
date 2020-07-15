package com.thesis.apphealth.ui.symptomcheck;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thesis.apphealth.R;
import com.thesis.apphealth.adapter.symptomSearchAdapter;

import java.util.ArrayList;

public class SymptomCheck extends Fragment implements symptomSearchAdapter.chooseSymptomListener{

    private SymptomCheckViewModel mViewModel;
    Button btn_continue, btn_chongmat,btn_ho,btn_daudau,btn_dayhoi,btn_tieuchay,btn_chuotrut,btn_buonnon,btn_metmoi,btn_sot;
    TextInputEditText searchsymtomtext;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> symptomList;
    symptomSearchAdapter searchAdapter;;
    ChipGroup chipGroup;
    ArrayList <String> chipList;
    ImageView imageView;
    TextView textView;
    Chip chip;
    ArrayList<String> choosedList = new ArrayList<>();
    public static SymptomCheck newInstance() {
        return new SymptomCheck();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                chipGroup.removeAllViews();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root= inflater.inflate(R.layout.symptom_check_fragment, container, false);
        searchsymtomtext=root.findViewById(R.id.searchsymtomtext);
        recyclerView = root.findViewById(R.id.list_symptom);
        btn_continue=root.findViewById(R.id.continuesymptomcheck);
        chipGroup=root.findViewById(R.id.chip_group);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        symptomList = new ArrayList<>();
        imageView=root.findViewById(R.id.emptysymptom);
        textView=root.findViewById(R.id.emptysymptom_text);
        chipList=new ArrayList<>();


/*        listener =new symptomSearchAdapter.chooseSymptomListener() {
            @Override
            public void chooseSymptomClick(int position) {
                Toast.makeText(getContext(),"Position"+position,Toast.LENGTH_SHORT).show();
            }
        };*/
        searchsymtomtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()){
                    setAdapter(editable.toString());
                } else{
                    symptomList.clear();
                    recyclerView.removeAllViews();
                }

            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i<chipGroup.getChildCount();i++){
                    Chip chip=(Chip)chipGroup.getChildAt(i);
                    choosedList.add(chip.getText().toString());
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("choosedList", choosedList);
                if((bundle.getStringArrayList("choosedList")).size()!=0) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_diagnosis_to_nav_listdisease,bundle);
                        }else
                            Toast.makeText(getActivity(),"Hãy chọn ít nhất một triệu chứng", Toast.LENGTH_SHORT).show();
            }
        });
        btn_ho=root.findViewById(R.id.cough);
        btn_buonnon=root.findViewById(R.id.nausea);
        btn_sot=root.findViewById(R.id.fever);
        btn_metmoi=root.findViewById(R.id.fatigue);
        btn_chuotrut=root.findViewById(R.id.musclecramp);
        btn_tieuchay=root.findViewById(R.id.diarhea);
        btn_dayhoi=root.findViewById(R.id.bloating);
        btn_chongmat=root.findViewById(R.id.dizziness);
        btn_daudau=root.findViewById(R.id.headache);
        btn_chongmat.setOnClickListener(listener);
        btn_daudau.setOnClickListener(listener);
        btn_dayhoi.setOnClickListener(listener);
        btn_tieuchay.setOnClickListener(listener);
        btn_chuotrut.setOnClickListener(listener);
        btn_metmoi.setOnClickListener(listener);
        btn_sot.setOnClickListener(listener);
        btn_ho.setOnClickListener(listener);
        return root;
    }
    private final View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            LayoutInflater minflater= (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (view.getId()) {
                case R.id.cough:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("ho");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.bloating:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("đầy hơi");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.diarhea:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("tiêu chảy");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.fever:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("sốt");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.musclecramp:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("chuột rút");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.nausea:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("buồn nôn");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.fatigue:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("mệt mỏi");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.dizziness:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("chóng mặt");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
                case R.id.headache:
                    clearEmptyNote();
                    chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                    chip.setText("đau đầu");
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            chipGroup.removeView(view);
                        }
                    });
                    chipGroup.addView(chip);
                    break;
            }
        }
    };
    private void setAdapter(final String seacrchedString) {

        databaseReference.child("1zprxaVyJAD0wLz8BT-rXHotENYmHrlBB_x0XrMGQ5O0").child("symptom").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                symptomList.clear();
                recyclerView.removeAllViews();
                int count =0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    String symptomID=snapshot.getKey();
                    String symptomName=snapshot.child("symptomName").getValue(String.class);
                    if (symptomName.toLowerCase().contains(seacrchedString.toLowerCase())){
                        symptomList.add(symptomName);
                        count ++;
                    }
                    if(count ==15){
                        break;
                    }
                }
                searchAdapter = new symptomSearchAdapter(getContext(), symptomList, new symptomSearchAdapter.chooseSymptomListener() {
                    @Override
                    public void chooseSymptomClicked(final int position) {
                        clearEmptyNote();
                        LayoutInflater minflater= (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        String text = symptomList.get(position);
                        chip = (Chip) minflater.inflate(R.layout.chip_item,null);
                        chip.setText(text);
                        chip.setOnCloseIconClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                chipGroup.removeView(view);
                            }
                        });
                        chipGroup.addView(chip);
                    }
                });
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void clearEmptyNote(){
        imageView.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SymptomCheckViewModel.class);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
        // TODO: Use the ViewModel
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Your callback initialization here
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //Your callback initialization here
    }
    @Override
    public void chooseSymptomClicked(int position) {

    }
}
