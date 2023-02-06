package com.example.Temiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gamesetting extends AppCompatActivity {
    static int num_team = 2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ImageView im1;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2,radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamesetting);
        radioGroup = (RadioGroup) findViewById(R.id.radio1);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        im1 = (ImageView) findViewById(R.id.dice1);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.radioButton1:
                        myRef.child("Temi1/led").setValue(true);
                        myRef.child("Temi2/led").setValue(true);
                        myRef.child("Temi3/led").setValue(true);
                        myRef.child("Temi4/led").setValue(true);
                        myRef.child("Temi5/led").setValue(false);
                        myRef.child("Temi6/led").setValue(false);
                        myRef.child("Temi7/led").setValue(false);
                        myRef.child("Temi8/led").setValue(false);
                        num_team = 2;
                        break;
                    case R.id.radioButton2:
                        myRef.child("Temi1/led").setValue(true);
                        myRef.child("Temi2/led").setValue(true);
                        myRef.child("Temi3/led").setValue(true);
                        myRef.child("Temi4/led").setValue(true);
                        myRef.child("Temi5/led").setValue(true);
                        myRef.child("Temi6/led").setValue(true);
                        myRef.child("Temi7/led").setValue(false);
                        myRef.child("Temi8/led").setValue(false);
                        num_team = 3;
                        break;
                    case R.id.radioButton3:
                        myRef.child("Temi1/led").setValue(true);
                        myRef.child("Temi2/led").setValue(true);
                        myRef.child("Temi3/led").setValue(true);
                        myRef.child("Temi4/led").setValue(true);
                        myRef.child("Temi5/led").setValue(true);
                        myRef.child("Temi6/led").setValue(true);
                        myRef.child("Temi7/led").setValue(true);
                        myRef.child("Temi8/led").setValue(true);
                        num_team = 4;
                        break;
                }
            }
        });




        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("Permission").setValue("1");
                Intent intent1 = new Intent(getApplicationContext(),Home.class);
                startActivity(intent1);
            }
        });

    }


    private void startAnimation(){
        Animation rotateImage= AnimationUtils.loadAnimation(this,R.anim.anim);
        im1.startAnimation(rotateImage);
    }
    }