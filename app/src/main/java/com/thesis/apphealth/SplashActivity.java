package com.thesis.apphealth;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    Animation topAnim,bottomAnim;
    ImageView image;
    private static int SPLASH_SCREEN=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*------------------------splash screen-------------------------*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);
        /*------------------------animation-------------------------*/
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        /*------------------------hook-------------------------*/
        image=findViewById(R.id.logo_image);
/*        logo=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView2);*/
        image.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,activity_login.class);
/*                startActivity(intent);
                finish();*/
                Pair[] pairs=new Pair[1];
                pairs[0]=new Pair<View,String>(image,"logo_image");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                }
            }
        },SPLASH_SCREEN);
    }
}
