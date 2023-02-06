package com.example.Temiproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class finish extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        Button finishbtn= (Button)findViewById(R.id.finishbtn);
        TextView textview = (TextView)findViewById(R.id.textView2);


        textview.setText("team "+Quiz.winner+" win!");

        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                for(int i = 1; i<9;i++) //initialize location of temis
                {
                    myRef.child("Temi_loc/temi"+i).setValue(0);
                }
            }
        });
    }



}
