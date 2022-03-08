package com.example.sunateraho;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sunateraho.MainActivity;
import com.example.sunateraho.R;

public class splashScreen extends AppCompatActivity {

    private  static  int SPLASH_SCREEN =2500;

    ImageView imageView,imageView2;

    Animation top, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageview2);



        top = AnimationUtils.loadAnimation(this, R.anim.top);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom);
        imageView.setAnimation(top);
        imageView2.setAnimation(bottom);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, base.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }
}
