package com.thesis.apphealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneNo extends AppCompatActivity {
    Button verify_btn;
    String verificationCodeBySystem;
    EditText verifyCodeEnteredByTheUser;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone_no);
        verify_btn=findViewById(R.id.verify_btn);
        verifyCodeEnteredByTheUser=findViewById(R.id.otpnumber);
        progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        
        String phoneNo = getIntent().getStringExtra("phoneNo");
        sendVeriFicationCodeToUser(phoneNo);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = verifyCodeEnteredByTheUser.getText().toString();
                if(code.isEmpty()||code.length()<6){
                    verifyCodeEnteredByTheUser.setError("OTP có ít nhất 6 chữ số");
                    verifyCodeEnteredByTheUser.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });

    }

    private void sendVeriFicationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84"+phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        String code= phoneAuthCredential.getSmsCode();
        if(code!=null){
            progressBar.setVisibility(View.VISIBLE);
            verifyCode(code);
        }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneNo.this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    };
    private void verifyCode(String CodeByUser){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem,CodeByUser);
        signIntheUserByCredential(credential);

    }
    private void signIntheUserByCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneNo.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),activity_login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(VerifyPhoneNo.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
