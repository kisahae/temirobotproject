package com.example.Temiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ImageView im1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        im1 = findViewById(R.id.puzzle);
        startAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent1 = new Intent(getApplicationContext(), Quiz.class);
                startActivity(intent1);
            }
        },5000);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                },5000);
            }
        });
    }
    private void startAnimation(){
        Animation rotateImage= AnimationUtils.loadAnimation(this,R.anim.anim);
        im1.startAnimation(rotateImage);
    }
}