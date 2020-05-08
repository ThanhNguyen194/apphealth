package com.thesis.apphealth;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class activity_login extends AppCompatActivity {
    Button callSignUp,login_btn;
    ImageView image;
    TextView logoText;
    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        callSignUp = findViewById(R.id.signup);
        //Hooks
        image=findViewById(R.id.logo_image);
        logoText=findViewById(R.id.login_text);
        username=findViewById(R.id.account);
        password=findViewById(R.id.password);
        login_btn=findViewById(R.id.login_btn);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_login.this,activity_signup.class);
                Pair[]pairs= new Pair[1];
                pairs[0]=new Pair<View,String>(image,"logo_image");
                /*pairs[1]=new Pair<View,String>(logoText,"login_text");
                pairs[2]=new Pair<View,String>(username,"username_trans");
                pairs[3]=new Pair<View,String>(password,"password_trans");
                pairs[4]=new Pair<View,String>(login_btn,"login_trans");
                pairs[5]=new Pair<View,String>(callSignUp,"login_signup_trans");*/
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity_login.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(view);
            }
        });
    }
    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            username.setError("Nhập tài khoản");
            return false;
        }else if(!val.matches(noWhiteSpace)){
            username.setError("Không để khoảng trắng");
            return false;
        }else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        if(val.isEmpty()){
            password.setError("Nhập mật khẩu");
            return false;
        }else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    public void loginUser(View view){
        if(!validateUsername()|!validatePassword()){
            return;
        }else {
            isUser();
        }
    }
    private void isUser(){
        final String userEnteredUsername=username.getEditText().getText().toString().trim();
        final String userEnteredPassword=password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser=reference.orderByChild("userName").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFormDB=dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if(passwordFormDB.equals(userEnteredPassword)){
                        String nameFormDB=dataSnapshot.child(userEnteredUsername).child("fullName").getValue(String.class);
                        String phoneNoFormDB=dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFormDB=dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String usernameFormDB=dataSnapshot.child(userEnteredUsername).child("userName").getValue(String.class);
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        password.setError("Mật khẩu không chính xác");
                    }
                }else
                    username.setError("Tài khoản không tồn tại");
                    username.requestFocus();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void setCallSignUpScreen(View view){

    }
}
