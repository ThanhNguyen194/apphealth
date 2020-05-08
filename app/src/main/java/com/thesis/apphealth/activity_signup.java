package com.thesis.apphealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_signup extends AppCompatActivity {
    TextInputLayout regFullName,regUsername,regPassword,regEmail,regPhoneNo;
    Button regBtn,loginSignupBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        regFullName=findViewById(R.id.reg_fullname);
        regUsername=findViewById(R.id.reg_username);
        regEmail=findViewById(R.id.reg_email);
        regPhoneNo=findViewById(R.id.reg_phonenumber);
        regPassword=findViewById(R.id.reg_password);
        regBtn=findViewById(R.id.reg_btn);
        loginSignupBtn=findViewById(R.id.login_signup_btn);
        rootNode =FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(view);
                /*Intent intent = new Intent(activity_signup.this,activity_login.class);
                startActivity(intent);
                finish();*/
            }
        });
        loginSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_signup.this,activity_login.class);
                startActivity(intent);
                finish();
            }
        });

    }private Boolean validateName(){
        String val = regFullName.getEditText().getText().toString();
        if(val.isEmpty()){
            regFullName.setError("Nhập tên");
            return false;
        }else{
            regFullName.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateUsername(){
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regUsername.setError("Nhập tài khoản");
            return false;
        }else if(val.length()>=15){
            regUsername.setError("Nhập quá nhiều ký tự");
            return false;
        }else if(!val.matches(noWhiteSpace)){
            regUsername.setError("Không để khoảng trắng");
            return false;
        }else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateEmail(){
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            regEmail.setError("Nhập email");
            return false;
        }else if(!val.matches(emailPattern)){
            regEmail.setError("Email không đúng định dạng");
            return false;
        }else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePhoneNo(){
        String val = regPhoneNo.getEditText().getText().toString();
        if(val.isEmpty()){
            regPhoneNo.setError("Nhập số điện thoạn");
            return false;
        }else{
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val = regPassword.getEditText().getText().toString();
        String passwordVal ="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
        /*"^"+
                //"(?=.*[0-9])"+                  //at least 1 digit
                //"(?=.*[a-z])"+                  //at least 1 lower case letter
                //"(?=.*[A-Z])"+                  //at least 1 upper case letter
                "(?=.*[a-zA-Z])"+               //any letter
                "(?=.*[@#$%^&+=])"+             //at least 1 special character
                "?=\\S+$"+                      //no white space
                ".{6,}"+                        //at least 6 character
                "$";*/
        if(val.isEmpty()){
            regPassword.setError("Nhập mật khẩu");
            return false;
        }else if(!val.matches(passwordVal)){
            regPassword.setError("Mật khẩu quá yếu");
            return false;
        }else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }

    }
    public void registerUser(View view){
        if(!validateName()|!validatePassword()|!validateEmail()|!validateUsername()|!validatePhoneNo()){
        return;
    }
        String FullName=regFullName.getEditText().getText().toString();
        String UserName=regUsername.getEditText().getText().toString();
        String Email=regEmail.getEditText().getText().toString();
        String PhoneNo=regPhoneNo.getEditText().getText().toString();
        String Password=regPassword.getEditText().getText().toString();
        Intent intent = new Intent(activity_signup.this,VerifyPhoneNo.class);
        intent.putExtra("phoneNo",PhoneNo);
        startActivity(intent);

        //UserInfomation userInfomation = new UserInfomation(FullName,UserName,Email,PhoneNo,Password);
        //reference.child(UserName).setValue(userInfomation);
    }
}
