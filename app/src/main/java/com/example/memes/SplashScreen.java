package com.example.memes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;


public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;
    
    Animation topAnim,bottomAnim;
    ImageView imageMeme;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Animatons
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        imageMeme = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);

        imageMeme.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(SplashScreen.this,MainActivity.class);
               startActivity(intent);
               finish();
           }
       },(long)SPLASH_SCREEN);
    }
}