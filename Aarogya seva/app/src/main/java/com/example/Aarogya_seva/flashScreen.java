package com.example.Aarogya_seva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class flashScreen extends AppCompatActivity {
    private static int timeout=4000;
    TextView txt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        txt=findViewById(R.id.txt);
        img=findViewById(R.id.img);

        Animation animation = AnimationUtils.loadAnimation(flashScreen.this,R.anim.myanim);
        img.startAnimation(animation);
        txt.startAnimation(animation);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(flashScreen.this,StartActivity.class);
                startActivity(intent);
                finish();

            }
        },timeout);
    }
}

