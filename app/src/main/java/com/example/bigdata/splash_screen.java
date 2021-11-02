package com.example.bigdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class splash_screen extends AppCompatActivity {
    private static  int SPLASH_SCREEN= 3200;
    Animation topAnim,bottomAnim;
    TextView big,slogan;
    LottieAnimationView anima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        big=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView2);
        big=findViewById(R.id.textView);
        anima=findViewById(R.id.aanim);
        anima.setAnimation(topAnim);
        big.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splash_screen.this, Login.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_SCREEN);
    }
}

